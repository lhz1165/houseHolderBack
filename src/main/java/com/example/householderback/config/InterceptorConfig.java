package com.example.householderback.config;

import com.example.householderback.utils.TokenArgumentResolver;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${file.address}")
    String fileAddress;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .excludePathPatterns("/login", "/register,/static/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其中headImage表示访问的前缀。
        // file:+fileAddress 是文件真实的存储路径
        registry.addResourceHandler("/headImage/**").addResourceLocations("file:"+fileAddress);
    }


    /**
     * 获取用户对象
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenArgumentResolver());
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 找出默认的 MappingJackson2HttpMessageConverter 进行配置
        converters.stream().filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .findFirst().ifPresent(converter -> {

            SimpleModule simpleModule = new SimpleModule();

            simpleModule.addSerializer(BigDecimal.class, BigDecimalToStringSerializer.INSTANCE);

            // Date 序列化与反序列化
            simpleModule.addSerializer(Date.class, DateToStringSerializer.INSTANCE);
            simpleModule.addDeserializer(Date.class, DateDeserializer.INSTANCE);

            // LocalDateTime 序列化与反序列化
            simpleModule.addSerializer(LocalDateTime.class, LocalDateTimeToStringSerializer.INSTANCE);
           // simpleModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

            ((MappingJackson2HttpMessageConverter) converter).getObjectMapper().registerModule(simpleModule);
        });
    }

    public static class BigDecimalToStringSerializer extends JsonSerializer<BigDecimal> {
        public final static BigDecimalToStringSerializer INSTANCE = new BigDecimalToStringSerializer();

        BigDecimalToStringSerializer() {
        }

        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(value.stripTrailingZeros().toPlainString());
        }
    }


    public static class DateToStringSerializer extends JsonSerializer<Date> {
        public final static DateToStringSerializer INSTANCE = new DateToStringSerializer();

        DateToStringSerializer() {
        }

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(String.valueOf(value.getTime()));
        }
    }

    public static final class DateDeserializer extends JsonDeserializer<Date> {
        public static final DateDeserializer INSTANCE = new DateDeserializer();

        DateDeserializer() {
        }

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            String s = jsonParser.getText().trim();
            try {
                return new Date(Long.parseLong(s));
            } catch (Exception ignored) {
                return null;
            }
        }
    }

    public static class LocalDateTimeToStringSerializer extends JsonSerializer<LocalDateTime> {
        public final static LocalDateTimeToStringSerializer INSTANCE = new LocalDateTimeToStringSerializer();

        LocalDateTimeToStringSerializer() {
        }

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(String.valueOf(value.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()));
        }
    }


//    public static final class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
//        public static final LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();
//
//        LocalDateTimeDeserializer() {
//        }
//
//        @Override
//        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//
//            String s = jsonParser.getText().trim();
//            try {
//                long timestamp = Long.parseLong(s) / 1000;
//                return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
//            } catch (Exception ignored) {
//                return LocalDateTime.parse(s);
//            }
//        }
//    }
}
