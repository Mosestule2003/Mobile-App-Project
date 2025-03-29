Carveal Mobile App - Technical Documentation

Overview

Carveal is an Android application that provides an integrated platform for vehicle diagnostics, service booking, notifications, and real-time weather updates. The application leverages AI-based APIs for vehicle analysis, supports a comprehensive booking workflow, and implements persistent storage for notifications.

Note: The login and sign-in modules are currently implemented as mockups. "THESE ARE TO BE CHANGED"

Architecture & Modules

1. Application Layers

- Presentation Layer:
  - Activities & Fragments: Manage user interactions and UI rendering.
  - RecyclerView & Adapters: Dynamically display notifications and other lists.
  
- Business Logic Layer:  
  - Booking Flow: Handles scheduling, confirmation, and payment processing.
  - Notification Management: Manages real-time notifications, including time updates.
  - API Integration: Interacts with external APIs for weather updates and AI-driven vehicle analysis.

- Data Layer:  
  - SharedPreferences with Gson: Persists notifications locally.
  - Parcelable Data Objects: Enables efficient inter-activity communication (e.g., `BookingData`).

### 2. Key Modules & Components

#### Loading & Authentication

- Loading_page:  
  - Plays a video (logo animation) using `VideoView` from the `res/raw` directory.
  - Utilizes a `Handler` to transition after a 3000ms delay to `sign_in_page`.
  
- `sign_in_page`:  
  - Implements edge-to-edge layout handling using `EdgeToEdge.enable(this)` and `WindowInsetsCompat`.
  - Currently a mockup; authentication logic will be implemented by a designated team member.

#### Booking Flow

- `activity_schedule`:  
  - UI Elements:  
    - `CalendarView`: For date selection.
    - `RadioGroup`: For choosing a time slot.
    - `Spinner`: For selecting a pickup location.
  - Data Passing:  
    - Captures and formats user selections.
    - Bundles data (including pricing details) into an `Intent` for `activity_confirmation`.
    
- `activity_confirmation`:  
  - Displays selected booking details (date, time, location).
  - Provides options to cancel or confirm the booking.
  - On confirmation, triggers the payment workflow by launching `activity_payment`.
  
- `activity_payment`:  
  - Handles payment processing with UI elements including:
    - `RadioGroup` for payment options.
    - `CardView` for card selection and display.
    - Input fields (`EditText`) for card details.
  - On payment confirmation, navigates to the notifications screen.
  
- Detail Views:  
  - `activity_booking_confirmation_detail`: Presents detailed booking confirmation data.
  - `activity_booking_reminder_detail`: Displays booking reminders with dynamically calculated appointment times.

#### Notifications

- `activity_notification`:  
  - Uses a `RecyclerView` combined with a `NotificationAdapter` to list notifications.
  - Implements periodic updates (every 60 seconds) via a `Handler` that triggers `notifyDataSetChanged()`.
  
- `NotificationAdapter`:  
  - Binds `Notification` objects to the UI.
  - Formats relative timestamps (minutes/hours/days ago).
  - Sets icons based on notification type (e.g., booking confirmation, booking reminder).
  - Handles click events to launch detailed views using `Intent` routing.

- `NotificationManager`:  
  - Manages the persistence of notifications using `SharedPreferences`.
  - Serializes and deserializes a list of `Notification` objects with Gson.
  - Implements methods:
    - `saveNotification()`: Inserts new notifications at the head of the list.
    - `loadNotifications()`: Retrieves persisted notifications.
    - `saveNotifications()`: Commits updates to local storage.

#### AI and Vehicle Analysis

- `activity_vehicle_info_analysis`:  
  - Integrates with an external AI API (via OkHttp) to retrieve potential vehicle concerns.
  - Constructs a JSON payload with vehicle details (model, year, transmission, fuel type) for the API.
  - Parses the API response asynchronously and displays the results.
  - On confirmation, directs users to `activity_schedule` to book a service.
  
- `BookingData`:  
  - Implements `Parcelable` to encapsulate booking details:
    - Fields: date, time, location, total price, and appointment time.
  - Facilitates efficient data passing between activities.

#### Weather Updates

- `activity_weather_detail`:  
  - Displays weather details by invoking `WeatherService`.
  
- `WeatherService`:  
  - Fetches current weather conditions from the WeatherAPI.
  - Implements asynchronous network requests (via deprecated `AsyncTask` in the current version; consider migrating to modern alternatives such as `ExecutorService` or Kotlin Coroutines).
  - Parses JSON response to extract:
    - Location, condition text, temperature in Celsius, and a flag indicating if the weather is favorable.
  - Provides a callback mechanism (`WeatherCallback`) to deliver results to the UI.

## Development & Interaction

### How to Run

1. Project Setup:  
   - Ensure all dependencies (including OkHttp, Gson, and AndroidX libraries) are properly configured in your `build.gradle` files.
   - Verify that API keys (for WeatherAPI and AI API) are correctly set in their respective classes.

2. Launching the Application:  
   - The app is configured to start with `Loading_page`, which automatically navigates to `sign_in_page` after a brief video animation.
   - Follow the booking flow to test scheduling, confirmation, and payment functionalities.

3. Testing Notifications:  
   - Notifications are automatically added to the `NotificationManager` and displayed in `activity_notification`.
   - Use the developer console or simulate API responses to test various notification types.

4. Network Requests & API Integration:  
   - Ensure network connectivity.
   - Test API endpoints (weather and AI vehicle analysis) by simulating or using valid parameters.

Future Enhancements

- User Profile Implementation:  
  - Pending development; will integrate user data display and profile management.
  
- AI Implementation Enhancements:  
  - The RecyclerView component will be extended to display dynamic AI analysis data.
  - Additional UI actions will be integrated to support AI-driven insights.

- Authentication:
  - Replace the mockup sign-in module with fully functional authentication logic as developed by the designated team member.
