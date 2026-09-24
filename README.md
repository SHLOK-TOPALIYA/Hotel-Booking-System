<div align="center">

# 🏨 Hotel Booking System

### A Java-based hotel reservation system built around object-oriented design, date-aware availability, booking workflows, payments, cancellations, and refunds.

![Java](https://img.shields.io/badge/Java-24-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object--Oriented-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/status-completed-brightgreen?style=for-the-badge)
![CLI](https://img.shields.io/badge/interface-CLI-purple?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)

</div>

---

## ✦ Overview

**Hotel Booking System** is a console-based Java application that models the core workflow of a hotel reservation platform.

The system allows customers to explore hotels and room types, check room availability for specific dates, create bookings, process simulated payments, generate booking vouchers, and handle cancellations with refund policies.

The project focuses on translating a real-world booking domain into a **clean object-oriented design**, where each major responsibility is represented by a dedicated class.

> **The core idea:** a room is not simply "available" or "unavailable" — its availability depends on the requested date range and existing reservations.

---

## 🎯 What the System Solves

A hotel room can have multiple bookings over time, but two bookings must not overlap.

For a requested interval:

```text
[Check-in, Check-out)
