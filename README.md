# 🛍️ Cosmetics Store — Android E-Commerce App

A full-featured e-commerce Android application for cosmetics and skincare products, built as a high school final project.

> Built independently without AI coding tools.

---

## 📱 Screenshots

<table>
  <tr>
    <td align="center"><img src="screenshots/01_welcome.png" width="180"/><br/><b>Welcome Screen</b></td>
    <td align="center"><img src="screenshots/02_login.png" width="180"/><br/><b>Login</b></td>
    <td align="center"><img src="screenshots/03_register.png" width="180"/><br/><b>Register</b></td>
    <td align="center"><img src="screenshots/04_admin_login.png" width="180"/><br/><b>Admin Login</b></td>
  </tr>
  <tr>
    <td align="center"><img src="screenshots/06_home.png" width="180"/><br/><b>Home — Categories</b></td>
    <td align="center"><img src="screenshots/05_navigation.png" width="180"/><br/><b>Navigation Drawer</b></td>
    <td align="center"><img src="screenshots/07_cosmetics.png" width="180"/><br/><b>Cosmetics</b></td>
    <td align="center"><img src="screenshots/08_perfumes.png" width="180"/><br/><b>Perfumes</b></td>
  </tr>
  <tr>
    <td align="center"><img src="screenshots/09_product_list.png" width="180"/><br/><b>Product List & Filter</b></td>
    <td align="center"><img src="screenshots/10_nail_care.png" width="180"/><br/><b>Nail Care</b></td>
    <td align="center"><img src="screenshots/11_product_detail.png" width="180"/><br/><b>Product Detail</b></td>
    <td align="center"><img src="screenshots/12_cart.png" width="180"/><br/><b>Shopping Cart</b></td>
  </tr>
  <tr>
    <td align="center"><img src="screenshots/13_payment.png" width="180"/><br/><b>Payment</b></td>
    <td align="center"><img src="screenshots/14_paypal.png" width="180"/><br/><b>PayPal</b></td>
    <td></td>
    <td></td>
  </tr>
</table>

---

## ✨ Features

### 👤 User Side
- **User Authentication** - register and login with username & password
- **Product Catalog** - browse by category: Cosmetics, Perfumes, Nail Care, Hair
- **Sub-categories** - Cosmetics split into Lips, Eyes, Face
- **Smart Filtering** - filter by price range, brand name, or product description
- **Product Detail** - view product image, price, rating, and reviews
- **Favorites** - add/remove products with SQL persistence
- **Shopping Cart** - persists across sessions (saved to DB on exit, reloaded on login)
- **Order History** - full order tracking saved per user
- **Payment** - Debit / Visa / MasterCard or PayPal integration
- **Push Notifications** - scheduled notifications that fire even after the app is closed

### 🔧 Admin Side
- **Admin Panel** - separate login for administrators
- **Inventory Management** - add, update, and delete products from the catalog

---

## 🏗️ Technical Highlights

| Component | Implementation |
|-----------|---------------|
| Language | Java |
| Database | MySQL (via PHP backend) |
| Persistence | SQL — cart & orders saved per user session |
| Notifications | Background Service — fires after app close |
| Payment | PayPal SDK + Credit/Debit Card |
| Navigation | Navigation Drawer + multi-level category browsing |
| Auth | Dual-role: User & Admin with separate login flows |
| Media | GIF display on splash screen |

---

## 🔑 Key Android Concepts Used

- **Activity** — multi-screen navigation
- **Intent** — explicit & implicit, passing data between screens
- **Service** — background push notifications
- **BroadcastReceiver** — system event handling
- **SQLite/MySQL** — local and remote data persistence

---

## 🛠️ Built With

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![PayPal](https://img.shields.io/badge/PayPal-003087?style=flat&logo=paypal&logoColor=white)

---

## 📂 App Flow

```
Splash Screen (GIF)
        ↓
   Login / Register
        ↓
   ┌────┴────┐
 User      Admin
   ↓          ↓
Main Menu  Inventory
   ↓       Management
 Browse
Products
   ↓
Favorites / Cart / Orders / Payment
```

---

## ⚠️ Setup Notes

`google-services.json` and `local.properties` are excluded from this
repository for security reasons. The app requires a MySQL backend
and PayPal SDK credentials to run.
