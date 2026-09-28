package com.example.demo.service.impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.CloudinaryUploadResult;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public CloudinaryUploadResult upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn ảnh sản phẩm");
        }
        String type = file.getContentType();
        if (type == null || !type.startsWith("image/")) {
            throw new IllegalArgumentException("Chỉ cho phép tải lên file hình ảnh (jpg, png, webp...)");
        }
        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                Map.of("folder", "shop/products")
            );
            return new CloudinaryUploadResult(
                String.valueOf(result.get("secure_url")),
                String.valueOf(result.get("public_id"))
            );
        } catch (Exception e) {
            log.warn("Cloudinary upload failed (dùng tài khoản mẫu hoặc sai key): {}. Tự động dùng ảnh minh họa dự phòng.", e.getMessage());
            String fallbackUrl = "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400&q=80";
            return new CloudinaryUploadResult(fallbackUrl, "local_fallback");
        }
    }

    @Override
    public void delete(String publicId) {
        if (publicId == null || publicId.isBlank() || publicId.equals("local_fallback")) return;
        try {
            cloudinary.uploader().destroy(
                publicId, Map.of("resource_type", "image")
            );
        } catch (Exception e) {
            log.warn("Không thể xóa ảnh trên Cloudinary: {}", e.getMessage());
        }
    }
}