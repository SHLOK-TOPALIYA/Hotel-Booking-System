<div align="center">

# 🏨 Hotel Booking System

### A Java-based hotel reservation system designed with object-oriented principles and date-aware booking logic.

<img src="https://img.shields.io/badge/Java-24-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
<img src="https://img.shields.io/badge/Design-Object--Oriented-blue?style=for-the-badge" alt="OOP">
<img src="https://img.shields.io/badge/Interface-CLI-purple?style=for-the-badge" alt="CLI">
<img src="https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge" alt="Status">

</div>

---

## 📌 Overview

**Hotel Booking System** is a console-based Java application that models the core workflow of a hotel reservation platform.

The system allows users to:

- Explore hotels and room types
- Check room availability for specific dates
- Select and book rooms
- Process simulated payments
- Generate booking vouchers
- View booking history
- Cancel confirmed bookings
- Calculate refunds according to a cancellation policy

The project focuses on applying **object-oriented design to a real-world booking domain**, with separate classes representing hotels, rooms, customers, bookings, payments, refunds, vouchers, and cancellation policies.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🏨 Hotel Management | Manage multiple hotels and their rooms |
| 🛏️ Room Types | Supports Standard, Deluxe, and Suite rooms |
| 📅 Date-Based Availability | Prevents overlapping reservations |
| 🔎 Room Selection | Finds available rooms by room type and date |
| 👤 Customer Management | Maintains customer information and booking history |
| 🧾 Booking Management | Creates, confirms, displays, and cancels bookings |
| 💳 Payment Simulation | Supports successful and failed payment scenarios |
| 🎟️ Voucher Generation | Generates a voucher after successful booking |
| ❌ Cancellation | Allows confirmed bookings to be cancelled |
| 💰 Refund Processing | Calculates refunds according to cancellation timing |
| 🧪 Test Scenarios | Includes seven functional test cases |

---

## 🏗️ System Architecture

The system is organized around the main entities involved in a hotel reservation workflow.

```text
                         ┌─────────────────┐
                         │   HotelChain    │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │      Hotel      │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │      Room       │
                         └────────┬────────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │     Booking     │
                         └───────┬─┬───────┘
                                 │ │
                   ┌─────────────┘ └─────────────┐
                   ▼                             ▼
            ┌─────────────┐               ┌─────────────┐
            │   Payment   │               │   Voucher   │
            └──────┬──────┘               └─────────────┘
                   │
                   ▼
            ┌─────────────┐
            │   Refund    │
            └─────────────┘
