package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.entity.Product;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository,
                                      UserRepository userRepository,
                                      ProductRepository productRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Tạo 2 Role chuẩn
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").build()));

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));

            // 2. Tạo tài khoản Admin
            User admin = userRepository.findByUsername("admin").orElseGet(() -> {
                User u = User.builder()
                        .username("admin")
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("System Administrator")
                        .enabled(true)
                        .role(adminRole)
                        .build();
                return userRepository.save(u);
            });

            // 3. Tạo tài khoản User (Thái Doãn Thịnh)
            User thinh = userRepository.findByUsername("thinh").orElseGet(() -> {
                User u = User.builder()
                        .username("thinh")
                        .email("thinhdt.qt@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Thái Doãn Thịnh")
                        .enabled(true)
                        .role(userRole)
                        .build();
                return userRepository.save(u);
            });

            // 4. thêm 10 sản phẩm mẫu
            if (productRepository.count() < 10) {
                List<Product> sampleProducts = List.of(
                    Product.builder()
                        .name("iPhone 16 Pro Max 256GB")
                        .description("Điện thoại Apple cao cấp nhất, khung viền Titan Sa Mạc sang trọng")
                        .price(new BigDecimal("34990000"))
                        .imageUrl("https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=500&q=80|mock_id_1")
                        .user(admin)
                        .build(),

                    Product.builder()
                        .name("Samsung Galaxy S24 Ultra")
                        .description("Tích hợp quyền năng Galaxy AI, camera zoom 100x và bút S-Pen thông minh")
                        .price(new BigDecimal("29990000"))
                        .imageUrl("https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=500&q=80|mock_id_2")
                        .user(admin)
                        .build(),

                    Product.builder()
                        .name("MacBook Pro 14 M3 Pro")
                        .description("Chip Apple M3 Pro mạnh mẽ, màn hình Liquid Retina XDR 120Hz sắc nét")
                        .price(new BigDecimal("49990000"))
                        .imageUrl("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=500&q=80|mock_id_3")
                        .user(admin)
                        .build(),

                    Product.builder()
                        .name("Tai nghe chống ồn Sony WH-1000XM5")
                        .description("Khả năng chống ồn chủ động hàng đầu thế giới, âm thanh Hi-Res đỉnh cao")
                        .price(new BigDecimal("6990000"))
                        .imageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500&q=80|mock_id_4")
                        .user(admin)
                        .build(),

                    Product.builder()
                        .name("Apple Watch Series 9 GPS 45mm")
                        .description("Cử chỉ chạm hai lần Double Tap mới mẻ, theo dõi sức khỏe và nhịp tim chính xác")
                        .price(new BigDecimal("10490000"))
                        .imageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500&q=80|mock_id_5")
                        .user(admin)
                        .build(),

                    Product.builder()
                        .name("Bàn phím cơ không dây Logitech MX Mechanical")
                        .description("Phím bấm Tactile êm ái, kết nối đa thiết bị qua Bluetooth và Logi Bolt")
                        .price(new BigDecimal("3290000"))
                        .imageUrl("https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=500&q=80|mock_id_6")
                        .user(thinh)
                        .build(),

                    Product.builder()
                        .name("Chuột công thái học Logitech MX Master 3S")
                        .description("Cuộn điện từ MagSpeed siêu nhanh, cảm biến 8000 DPI trên mọi bề mặt")
                        .price(new BigDecimal("2490000"))
                        .imageUrl("https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=500&q=80|mock_id_7")
                        .user(thinh)
                        .build(),

                    Product.builder()
                        .name("Màn hình đồ họa Dell UltraSharp U2723QE 4K")
                        .description("Tấm nền IPS Black độ tương phản 2000:1, chuẩn màu 98% DCI-P3 chuyên nghiệp")
                        .price(new BigDecimal("12890000"))
                        .imageUrl("https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=500&q=80|mock_id_8")
                        .user(thinh)
                        .build(),

                    Product.builder()
                        .name("iPad Air 6 M2 11 inch Wifi 128GB")
                        .description("Sức mạnh vượt trội từ chip M2, tương thích bút Apple Pencil Pro mới nhất")
                        .price(new BigDecimal("16990000"))
                        .imageUrl("https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=500&q=80|mock_id_9")
                        .user(thinh)
                        .build(),

                    Product.builder()
                        .name("Loa Bluetooth Marshall Stanmore III")
                        .description("Âm thanh cổ điển đậm chất Rock, công suất 80W lấp đầy không gian phòng khách")
                        .price(new BigDecimal("8990000"))
                        .imageUrl("https://images.unsplash.com/photo-1545454675-3531b543be5d?w=500&q=80|mock_id_10")
                        .user(thinh)
                        .build()
                );

                productRepository.saveAll(sampleProducts);
            }
        };
    }
}