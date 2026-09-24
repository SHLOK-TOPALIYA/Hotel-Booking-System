<div align="center">

# 🏨 Hotel Booking System

### A Java-based hotel reservation system designed with object-oriented principles and date-aware booking logic.

[![Java](https://img.shields.io/badge/Java-24-orange?style=for-the-badge\&logo=openjdk)](https://www.oracle.com/java/)
[![OOP](https://img.shields.io/badge/Design-Object--Oriented-blue?style=for-the-badge)](#-system-design)
[![CLI](https://img.shields.io/badge/Interface-CLI-green?style=for-the-badge)](#-application-menu)
[![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)](#-testing)

</div>

---

# 📌 Overview

**Hotel Booking System** is a console-based Java application that models the core workflow of a hotel reservation platform.

The system allows users to:

* 🏨 Explore hotels and room types
* 🛏️ Select and book rooms
* 📅 Check room availability for specific dates
* 🔎 Detect overlapping reservations
* 💳 Process simulated payments
* 🧾 Generate booking vouchers
* 📋 View booking history
* ❌ Cancel confirmed bookings
* 💰 Calculate refunds according to cancellation policy
* 🧪 Execute predefined functional test scenarios

The project focuses on applying **object-oriented design to a real-world booking domain**, with separate classes representing hotels, rooms, customers, bookings, payments, refunds, vouchers, and cancellation policies.

---

# ✨ Features

| Feature                        | Description                                         |
| ------------------------------ | --------------------------------------------------- |
| 🏨 **Hotel Management**        | Manage multiple hotels and their rooms              |
| 🛏️ **Room Types**             | Standard, Deluxe and Suite rooms                    |
| 📅 **Date-Based Availability** | Prevents overlapping reservations                   |
| 🔎 **Room Selection**          | Finds rooms available for requested dates           |
| 👤 **Customer Management**     | Maintains customer information and booking history  |
| 🧾 **Booking Management**      | Creates, confirms, displays and cancels bookings    |
| 💳 **Payment Simulation**      | Handles successful and failed payments              |
| 🎟️ **Voucher Generation**     | Generates a voucher after successful booking        |
| ❌ **Cancellation**             | Supports cancellation of confirmed bookings         |
| 💰 **Refund Processing**       | Calculates refunds according to cancellation timing |
| 🧪 **Functional Testing**      | Seven functional test scenarios                     |

---

# 🏗️ System Design

## Architecture

The system is organized around the following core domain entities:

```text
HotelChain
    │
    └── Hotel
          │
          └── Room
                │
                └── Booking
```

The booking process interacts with several supporting components:

```text
Customer
    │
    ▼
Booking
 ┌──┼───────────────┐
 ▼  ▼               ▼
Room Payment      Voucher
     │
     ▼
Cancellation
   Policy
     │
     ▼
  Refund
```

### Overall Domain Relationship

```text
HotelChain → Hotel → Room → Booking
                              │
             ┌────────────────┼────────────────┐
             │                │                │
             ▼                ▼                ▼
         Customer          Payment          Voucher
                                │
                                ▼
                     CancellationPolicy
                                │
                                ▼
                             Refund
```

---

# 📐 Class Structure

The major domain classes are:

```text
HotelChain
    │
    └── Hotel
          │
          └── Room
                │
                └── Booking
                      │
          ┌───────────┼────────────┐
          ▼           ▼            ▼
      Customer     Payment      Voucher

                    Booking
                       │
                       ▼
             CancellationPolicy
                       │
                       ▼
                    Refund
```

## Main Classes

| Class                | Responsibility                                                        |
| -------------------- | --------------------------------------------------------------------- |
| `HotelChain`         | Manages the collection of hotels                                      |
| `Hotel`              | Manages rooms belonging to a hotel and searches for available rooms   |
| `Room`               | Represents an individual hotel room and maintains booking information |
| `RoomType`           | Defines room category, capacity and nightly price                     |
| `Customer`           | Stores customer information and booking history                       |
| `Booking`            | Represents a reservation and coordinates its related objects          |
| `Payment`            | Simulates payment processing                                          |
| `CancellationPolicy` | Determines the applicable refund percentage                           |
| `Refund`             | Processes the calculated refund                                       |
| `Voucher`            | Generates booking confirmation information                            |
| `Main`               | Provides the command-line interface and application workflow          |

---

# 📊 UML Class Diagram

The UML class diagram documents:

* Classes
* Attributes
* Methods
* Relationships
* Associations
* Multiplicities
* Interactions between booking components

### UML Class Diagram

📐 **[View UML Class Diagram](docs/UML-Class-Diagram.pdf)**

The complete UML diagram is available in the `docs` directory.

---

# 🧠 Core Booking Logic

## 📅 Date-Based Room Availability

Date-based availability is one of the central pieces of logic in the system.

A room cannot be booked when the requested date range overlaps with an existing active reservation.

### Overlap Condition

```text
requestedCheckIn < existingCheckOut
AND
requestedCheckOut > existingCheckIn
```

If the condition evaluates to `true`, the requested booking overlaps with an existing reservation and the room is unavailable.

If the condition evaluates to `false`, the requested dates do not overlap and the room can be considered available.

---

## ✅ Back-to-Back Booking

Back-to-back bookings are allowed.

### Existing Reservation

```text
Jan 10 ───────────────── Jan 12
```

### New Reservation

```text
Jan 12 ───────────────── Jan 14
```

### Result

```text
✅ ALLOWED
```

The new booking begins exactly when the previous booking ends, so there is no date overlap.

---

## ❌ Overlapping Booking

### Existing Reservation

```text
Jan 10 ───────────────── Jan 12
```

### New Reservation

```text
       Jan 11 ───────────────── Jan 13
```

### Result

```text
❌ REJECTED
```

The requested reservation overlaps with the existing booking.

---

# 💰 Cancellation & Refund Policy

The cancellation policy is implemented separately through the `CancellationPolicy` component.

| Cancellation Time                  | Refund |
| ---------------------------------- | -----: |
| More than 72 hours before check-in |   100% |
| 24–72 hours before check-in        |    50% |
| Less than 24 hours before check-in |     0% |

This separation keeps cancellation rules independent from the main booking workflow.

---

# 🔄 Booking Workflow

The general booking process is:

```text
                    ┌──────────────────┐
                    │   Select Hotel   │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │   Select Room    │
                    │      Type        │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │    Enter Dates   │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │ Check Availability│
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │ Calculate Total  │
                    │     Amount       │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     Payment      │
                    └────────┬─────────┘
                             │
                    ┌────────┴────────┐
                    │                 │
                  FAIL             SUCCESS
                    │                 │
                    ▼                 ▼
              ┌───────────┐   ┌──────────────┐
              │  Booking  │   │    Booking   │
              │ Rejected  │   │   Confirmed  │
              └───────────┘   └───────┬──────┘
                                      │
                              ┌───────┴───────┐
                              │               │
                              ▼               ▼
                       ┌───────────┐   ┌────────────┐
                       │ Generate  │   │ Add Booking│
                       │  Voucher  │   │  to Room   │
                       └───────────┘   └────────────┘
```

---

# 🧩 Core Components

## 🏨 HotelChain

Manages the collection of hotels available in the system.

---

## 🏨 Hotel

Represents a hotel and manages the rooms associated with it.

It also provides functionality for searching and identifying rooms available for requested dates.

---

## 🛏️ Room

Represents an individual hotel room.

A room maintains its associated booking information and is used during availability checking.

---

## 🏷️ RoomType

Defines the category, capacity and nightly price of a room.

| Room Type | Capacity | Price / Night |
| --------- | -------: | ------------: |
| Standard  |        2 |        ₹3,000 |
| Deluxe    |        4 |        ₹5,000 |
| Suite     |        6 |        ₹8,000 |

---

## 👤 Customer

Stores customer information and maintains the customer's booking history.

---

## 🧾 Booking

Represents a hotel reservation.

A booking coordinates:

* Customer
* Room
* Check-in date
* Check-out date
* Payment
* Booking status
* Voucher

---

## 💳 Payment

Simulates the payment process.

The system supports:

* Successful payments
* Failed payments

A booking is confirmed only after successful payment processing.

---

## 📋 CancellationPolicy

Contains the cancellation and refund rules.

The policy determines the refund percentage based on the time between cancellation and check-in.

---

## 💰 Refund

Handles refund processing based on the refund percentage calculated by the cancellation policy.

---

## 🎟️ Voucher

Generates booking confirmation information after a successful reservation.

---

# 🛠️ Technology Stack

| Technology                      | Purpose                                   |
| ------------------------------- | ----------------------------------------- |
| **Java 24**                     | Application implementation                |
| **Object-Oriented Programming** | Domain and system design                  |
| **Java Collections Framework**  | Managing hotels, rooms and bookings       |
| **Java Time API**               | Date and time calculations                |
| **Command-Line Interface**      | User interaction                          |
| **Git & GitHub**                | Version control and repository management |

---

# 🧪 Testing

The system includes **seven functional test scenarios** covering the major booking workflows.

| Test Case | Scenario                      | Result |
| --------- | ----------------------------- | ------ |
| **TC01**  | Successful booking            | ✅ PASS |
| **TC02**  | Overlapping booking rejection | ✅ PASS |
| **TC03**  | Payment failure               | ✅ PASS |
| **TC04**  | Full refund cancellation      | ✅ PASS |
| **TC05**  | Partial / no refund           | ✅ PASS |
| **TC06**  | Non-overlapping bookings      | ✅ PASS |
| **TC07**  | Invalid room selection        | ✅ PASS |

---

## 🏆 Test Result

<div align="center">

# 7 / 7 PASS

### All required functional test scenarios passed successfully.

</div>

---

## 📄 Detailed Test Cases & Results

The complete test documentation contains:

* Test case ID
* Scenario
* Preconditions
* Test steps
* Expected result
* Actual result
* Execution status

🧪 **[View Test Cases & Results](docs/Test-Cases-and-Results.pdf)**

---

# 🚀 Getting Started

## Prerequisites

Before running the project, install:

* **JDK 24** or a compatible Java version
* **Git**
* A terminal / command prompt

---

## Verify Java Installation

```bash
java -version
javac -version
```

Expected output should indicate that Java/JDK is installed and available through the command line.

---

# 1️⃣ Clone the Repository

```bash
git clone https://github.com/SHLOK-TOPALIYA/Hotel-Booking-System.git
```

---

# 2️⃣ Enter the Project Directory

```bash
cd Hotel-Booking-System
```

---

# 3️⃣ Compile the Source Files

```bash
javac src/*.java
```

---

# 4️⃣ Run the Application

```bash
java -cp src Main
```

---

# 🖥️ Application Menu

When the application starts, the user can interact with the following menu:

```text
╔══════════════════════════════════════╗
║        HOTEL BOOKING SYSTEM          ║
╠══════════════════════════════════════╣
║ 1. View Hotels                       ║
║ 2. View Room Types                   ║
║ 3. View Available Rooms              ║
║ 4. Book a Room                       ║
║ 5. View My Bookings                  ║
║ 6. Cancel Booking                    ║
║ 7. Run Required Test Cases           ║
║ 8. Exit                              ║
╚══════════════════════════════════════╝
```

---

# 📂 Repository Structure

```text
Hotel-Booking-System/
│
├── src/
│   ├── Booking.java
│   ├── CancellationPolicy.java
│   ├── Customer.java
│   ├── Hotel.java
│   ├── HotelChain.java
│   ├── Main.java
│   ├── Payment.java
│   ├── Refund.java
│   ├── Room.java
│   ├── RoomType.java
│   └── Voucher.java
│
├── docs/
│   ├── UML-Class-Diagram.pdf
│   └── Test-Cases-and-Results.pdf
│
└── README.md
```

---

# 📚 Documentation

## 📐 UML Class Diagram

The UML documentation contains:

* Complete class structure
* Attributes
* Methods
* Relationships
* Associations
* Multiplicities

📐 **[Open UML Class Diagram](docs/UML-Class-Diagram.pdf)**

---

## 🧪 Test Cases & Results

The test documentation contains all seven functional test scenarios along with:

* Test case ID
* Scenario
* Preconditions
* Test steps
* Expected result
* Actual result
* Execution status

🧪 **[Open Test Cases & Results](docs/Test-Cases-and-Results.pdf)**

---

# 🔮 Future Improvements

The current version focuses on the core hotel reservation workflow.

Potential extensions include:

* 🌐 Web-based user interface
* 🗄️ Database persistence
* 🔐 Authentication and authorization
* 💳 Real payment gateway integration
* 📧 Email booking confirmations
* 🔎 Advanced hotel search
* 👨‍💼 Admin dashboard
* 📊 Booking analytics
* 🧪 Automated JUnit testing
* 🏨 Multi-property support
* ☁️ Cloud deployment

---

# 👨‍💻 Author

<div align="center">

## Shlok Topaliya

**Java • Object-Oriented Design • Software Engineering**

</div>

---

<div align="center">

### ⭐ If you find the project interesting, consider starring the repository.

**Built with Java · Designed with OOP · Tested with functional booking scenarios**

</div>
