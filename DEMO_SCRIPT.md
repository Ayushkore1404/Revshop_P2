# RevShop P2 - Live Demo Script

## 🎬 **Demo Preparation Checklist**

### **Before Starting**
- [ ] Backend running on `http://localhost:9090`
- [ ] Frontend running on `http://localhost:4200`
- [ ] Database populated with test data
- [ ] Clear browser cache
- [ ] Open Swagger UI in separate tab: `http://localhost:9090/swagger-ui/index.html`

---

## 🎪 **Live Demo Script**

### **Scene 1: Introduction & Overview (1 minute)**

**Speaker**: "Welcome to RevShop P2! Let me demonstrate our full-stack e-commerce platform built with Angular 17 and Spring Boot 3.2.5."

**Actions**:
- Show both frontend and backend running
- Display Swagger UI documentation
- Highlight the modern tech stack

---

### **Scene 2: User Registration & Authentication (2 minutes)**

**Speaker**: "Let's start with user registration and authentication system."

**Actions**:
1. **Navigate to Login page**
   - Click "Login" in navigation
   - Show "Don't have an account? Register"

2. **User Registration**
   - Fill registration form:
     ```
     Name: Demo User
     Email: demo@revshop.com
     Password: Demo123!
     Role: Buyer
     ```
   - Click "Register"
   - Show success message

3. **Login Process**
   - Login with new credentials
   - Show JWT token generation (in browser dev tools)
   - Display user dashboard

**Key Points to Mention**:
- "Password hashing using BCrypt"
- "JWT token for secure authentication"
- "Role-based access control"

---

### **Scene 3: Seller Dashboard & Product Management (3 minutes)**

**Speaker**: "Now let's switch to seller mode and demonstrate product management."

**Actions**:
1. **Logout and Login as Seller**
   - Logout current user
   - Login as existing seller:
     ```
     Email: elon@revshop.com
     Password: password123
     ```

2. **Add New Product**
   - Navigate to Seller Dashboard
   - Click "Add Product"
   - Fill product form:
     ```
     Product Name: Premium Laptop
     Description: High-performance laptop for professionals
     Price: 75000
     Stock: 15
     Low Stock Threshold: 5
     Category: Electronics
     ```
   - Click "Add Product"
   - Show success toast message

3. **Verify Product Listing**
   - Show product appears in seller's product list
   - Verify seller ID is correctly assigned
   - Show low stock threshold working

**Key Points to Mention**:
- "Configurable low stock thresholds per product"
- "Seller ID automatically assigned"
- "Real-time product updates"

---

### **Scene 4: Buyer Experience & Shopping (3 minutes)**

**Speaker**: "Let's experience the platform as a buyer and demonstrate the shopping flow."

**Actions**:
1. **Switch to Buyer Mode**
   - Logout seller
   - Login as buyer:
     ```
     Email: buyer@revshop.com
     Password: password123
     ```

2. **Browse Products**
   - Navigate to "Shop"
   - Show product grid with images
   - Demonstrate search functionality
   - Filter by category "Electronics"

3. **Product Details**
   - Click on "Premium Laptop" (just added)
   - Show product details including seller info
   - Demonstrate stock status display

4. **Add to Cart**
   - Click "Add to Cart"
   - Show cart count updates in real-time
   - Navigate to cart page

5. **Cart Management**
   - Show cart with added item
   - Update quantity
   - Show cart total calculation

**Key Points to Mention**:
- "Role-based stock visibility"
- "Real-time cart synchronization"
- "Image fallback system for missing images"

---

### **Scene 5: Order Processing (2 minutes)**

**Speaker**: "Let's complete the purchase and demonstrate order processing."

**Actions**:
1. **Checkout Process**
   - Click "Proceed to Checkout"
   - Show order summary
   - Confirm order

2. **Order Confirmation**
   - Show success message
   - Navigate to order history
   - Display recent order with status

**Key Points to Mention**:
- "Order tracking system"
- "Status updates throughout fulfillment"
- "Purchase history for users"

---

### **Scene 6: Technical Features & Documentation (2 minutes)**

**Speaker**: "Let me show you the technical excellence behind this platform."

**Actions**:
1. **Swagger Documentation**
   - Switch to Swagger UI tab
   - Show Product Management endpoints
   - Demonstrate "Try it out" feature
   - Test GET /api/products/all endpoint

2. **Testing Implementation**
   - Show test results:
     ```bash
     ./mvnw test
     ```
   - Display test coverage
   - Show ProductControllerTest

3. **Code Quality**
   - Briefly show project structure
   - Highlight clean architecture
   - Show service layer implementation

**Key Points to Mention**:
- "Comprehensive API documentation"
- "JUnit 4 and Mockito testing"
- "Clean code principles and SOLID architecture"

---

### **Scene 7: Advanced Features (1 minute)**

**Speaker**: "Let me highlight some advanced features that make RevShop stand out."

**Actions**:
1. **Low Stock Alerts**
   - Add product with low stock (2 items)
   - Show "Low Stock" warning
   - Demonstrate seller vs buyer visibility

2. **Responsive Design**
   - Resize browser window
   - Show mobile responsiveness
   - Demonstrate touch-friendly interface

3. **Error Handling**
   - Simulate network error
   - Show graceful error messages
   - Demonstrate toast notifications

**Key Points to Mention**:
- "Configurable business rules"
- "Mobile-first responsive design"
- "Comprehensive error handling"

---

## 🎯 **Demo Success Indicators**

### **✅ Must Work Flawlessly**
- User registration and login
- Product addition by seller
- Cart functionality
- Order processing
- Swagger UI accessibility

### **⚠️ Have Backup Plans**
- Screenshots of key features
- Pre-recorded video segments
- Static data for offline demo

### **🎪 Engagement Points**
- Ask audience about features they'd like to see
- Encourage questions during transitions
- Highlight technical decisions and trade-offs

---

## 🚨 **Troubleshooting Quick Fixes**

### **Backend Issues**
```bash
# Restart backend
./mvnw spring-boot:run

# Check database connection
# Verify application.properties
```

### **Frontend Issues**
```bash
# Restart Angular server
ng serve

# Clear browser cache
# Hard refresh: Ctrl+F5
```

### **Demo Flow Issues**
- Have screenshots ready
- Use Postman for API testing
- Switch to code walkthrough if needed

---

## 🎉 **Closing Lines**

**Speaker**: "RevShop P2 demonstrates enterprise-grade e-commerce development with modern technologies, comprehensive testing, and professional documentation. The platform is production-ready and scalable for real-world deployment."

**Final Action**: Show project dashboard with all features working

**Call to Action**: "Thank you! I'm happy to answer any questions about the implementation, architecture decisions, or future enhancements."

---

## 📱 **Mobile Demo Tips**

### **Responsive Design Showcase**
- Use browser dev tools mobile view
- Test touch interactions
- Show adaptive layouts

### **Performance Highlights**
- Fast loading times
- Smooth animations
- Efficient data loading

**Good luck with your presentation! 🚀**
