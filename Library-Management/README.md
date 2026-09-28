# 📚 Library Management RESTful API

Hệ thống Backend cung cấp RESTful API quản lý thư viện (Sách, Độc giả và Quy trình Mượn/Trả sách), được xây dựng trên nền tảng **Java 17** và **Spring Boot 3**.

Project được thiết kế chuẩn mực theo kiến trúc nhiều tầng (Layered Architecture), áp dụng Quản lý Giao dịch (`@Transactional`) để đảm bảo tính toàn vẹn dữ liệu tồn kho và chuẩn hóa xử lý ngoại lệ tập trung.

---

## 📌 Tính năng chính (Key Features)

- **Quản lý Sách (Book Management):**
    - Xem danh sách sách, tìm kiếm thông tin chi tiết theo ID.
    - Thêm mới sách kèm kiểm soát số lượng tồn kho (`quantity`).
- **Quản lý Độc giả (Reader Management):**
    - Quản lý danh sách độc giả.
    - Đăng ký độc giả mới với ràng buộc duy nhất cho Email.
- **Quy trình Mượn & Trả Sách (Borrow & Return Management):**
    - **Mượn sách:** Kiểm tra sự tồn tại của độc giả, kiểm tra sách tồn kho (`quantity > 0`), tự động trừ số lượng tồn kho và tạo phiếu mượn kèm ngày hẹn trả.
    - **Trả sách:** Cập nhật ngày trả thực tế, chuyển trạng thái phiếu mượn sang `RETURNED`, tự động cộng trả lại số lượng sách vào kho.
- **Xử lý lỗi tập trung (Global Exception Handling):** Bắt và trả về định dạng JSON nhất quán kèm HTTP Status Code thích hợp ($400\text{ Bad Request}$, $404\text{ Not Found}$) cho cả lỗi Validation và Business Logic.

---

## 🛠 Công nghệ sử dụng (Tech Stack)

- **Ngôn ngữ:** Java 17
- **Framework:** Spring Boot 3, Spring Data JPA
- **Cơ sở dữ liệu:** MySQL
- **Thư viện hỗ trợ:** Lombok, Jakarta Validation (`@Valid`, `@NotBlank`, `@Email`, `@Min`,...)
- **Kiểm thử API:** Postman
- **Quản lý mã nguồn:** Git, GitHub

---

## 📐 Kiến trúc & Điểm nổi bật kỹ thuật (Technical Highlights)

1. **Mô hình DTO (Data Transfer Object):** Tách biệt hoàn toàn giữa tệp giao tiếp Client (`BookDto`, `ReaderDto`, `BorrowRequestDto`) và tầng Persistence (`Entity`), tăng tính bảo mật và kiểm soát chính xác dữ liệu đầu vào.
2. **Quản lý Transactional (`@Transactional`):** Đảm bảo tính toàn vẹn dữ liệu (ACID) trong luồng mượn/trả sách. Nếu việc tạo phiếu mượn xảy ra lỗi, thao tác trừ số lượng sách tồn kho sẽ tự động Rollback.
3. **Data Validation:** Sử dụng `@Valid` kết hợp Jakarta Constraints ở tầng Controller để ngăn ngừa dữ liệu rác/không hợp lệ đi vào tầng Service.
4. **Xử lý ngoại lệ với `@RestControllerAdvice`:** Bắt toàn bộ lỗi Validation đầu vào và Runtime Exception để trả về phản hồi chuẩn hóa cho Client.

---