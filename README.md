Carveal Mobile App - Technical Documentation

Carveal is an Android application that provides an integrated platform for vehicle diagnostics, service booking, notifications, and real-time weather updates. The application utilizes AI-based APIs for vehicle analysis, integrates a comprehensive booking workflow, and incorporates persistent storage for notifications. The app is designed to handle multiple fragments for seamless navigation and data sharing between components.

Note: The login and sign-in modules are currently implemented as mockups. THESE ARE TO BE CHANGED and replaced with functional authentication mechanisms.

Architecture & Modules

1. Application Layers

- Presentation Layer:
  - Activities & Fragments: These components handle user interactions and UI rendering. Each fragment or activity corresponds to a distinct screen or feature (e.g., booking, notifications, vehicle diagnostics).
  - RecyclerView & Adapters: Used for dynamic data binding to the UI. The main menu uses a RecyclerView to manage the dynamic listing of menu items, while notifications and booking details are handled similarly through RecyclerView adapters.
  - Core Actionables: 
    - Login, Sign-Up, and Onboarding: Implemented as mockups with placeholder screens, to be replaced with actual authentication logic in future iterations.
    - Main Menu: Managed by RecyclerView, dynamically populated with menu items, allowing users to navigate to different fragments.
    - Bottom Navigation Bar: Contains interactive icons to navigate between fragments such as booking, notifications, vehicle analysis, and weather updates.

- Business Logic Layer:
  - Booking Flow: Includes logic for service booking, date and time selection, payment handling, and confirmation. This flow connects the scheduling interface (`activity_schedule`) to the confirmation (`activity_confirmation`), payment (`activity_payment`), and notification displays.
  - Notification Management: Responsible for handling real-time notifications, including displaying and updating notification lists through periodic updates. Utilizes a Handler for polling notifications every 60 seconds.
  - API Integration: Manages interactions with external APIs, such as weather data and AI-based vehicle diagnostics. The integration layer handles network requests (using OkHttp) and parses the responses to display relevant data.

- Data Layer:
  - SharedPreferences with Gson: Used to persist notifications and other transient data locally for offline use.
  - Parcelable Data Objects: Booking data, vehicle diagnostics, and other objects are serialized using Parcelable for efficient inter-activity communication (e.g., `BookingData`).
  - Local Database: For storing user preferences, historical bookings, and AI analysis results.

---

2. Key Modules & Components

### Loading & Authentication

- Loading_page:
  - Plays an animated logo using `VideoView` sourced from the `res/raw` directory.
  - Uses `Handler` to introduce a delay of 3 seconds (3000ms) before transitioning to `sign_in_page`.

- sign_in_page:
  - Implements edge-to-edge layout with `EdgeToEdge.enable(this)` and `WindowInsetsCompat` to handle modern Android UI design considerations (e.g., notch, navigation bar).
  - Current implementation serves as a mockup; functional authentication logic (e.g., Firebase, OAuth) will be implemented later by the designated team member.

### Booking Flow

- activity_schedule:
  - UI Elements:
    - `CalendarView`: Allows users to select a service date.
    - `RadioGroup`: Provides a selection mechanism for time slots.
    - `Spinner`: For selecting a pickup location.
  - Data Passing:
    - Captures user inputs and formats them into a data bundle.
    - This data is passed to `activity_confirmation` through an Intent.

- activity_confirmation:
  - Displays the formatted booking details (date, time, location) captured in `activity_schedule`.
  - Provides options to cancel or confirm the booking.
  - On confirmation, triggers the payment workflow and launches `activity_payment`.

- activity_payment:
  - UI elements:
    - `RadioGroup`: For payment method selection.
    - `CardView`: Displays selected payment method visually.
    - `EditText`: For entering payment details.
  - After payment confirmation, the app transitions to the notification screen (`activity_notification`).

- Detail Views:
  - activity_booking_confirmation_detail: Displays detailed confirmation data of the booking.
  - activity_booking_reminder_detail: Provides reminder notifications with dynamically calculated appointment times.

### Notifications

- activity_notification:
  - Uses a `RecyclerView` coupled with a `NotificationAdapter` to dynamically display a list of notifications.
  - The notification list is updated every 60 seconds via a `Handler` that calls `notifyDataSetChanged()` to refresh the list.
  
- NotificationAdapter:
  - Binds `Notification` objects to RecyclerView items.
  - Implements dynamic timestamp formatting (minutes/hours/days ago) for user-friendly readability.
  - Each notification type (e.g., booking confirmation, reminder) has associated icons and click behavior to open relevant details through `Intent` routing.

- NotificationManager:
  - Manages notification persistence in local storage via `SharedPreferences`.
  - Serialization and deserialization of `Notification` objects using Gson for efficient data retrieval.
  - Core methods:
    - `saveNotification()`: Inserts a new notification at the head of the list.
    - `loadNotifications()`: Retrieves notifications from persistent storage.
    - `saveNotifications()`: Commits updates to local storage.

### AI and Vehicle Analysis

- activity_vehicle_info_analysis:
  - Interfaces with an external AI API via OkHttp to retrieve potential vehicle concerns.
  - Constructs a JSON payload with vehicle details (e.g., model, year, transmission type, fuel type) and sends it to the API.
  - Handles the asynchronous response and updates the UI with vehicle diagnostics.
  - On successful analysis, the user is redirected to `activity_schedule` to book a service.

- BookingData:
  - Implements Parcelable for seamless data passing between activities.
  - Fields include: date, time, location, total price, and appointment time.

### Weather Updates

- activity_weather_detail:
  - Displays detailed weather information fetched via the `WeatherService` class.

- WeatherService:
  - Fetches real-time weather data from the WeatherAPI using an asynchronous network request.
  - The response is parsed to extract critical information such as location, condition text, temperature (in Celsius), and whether the weather is favorable for outdoor activities.
  - Implements a callback mechanism (`WeatherCallback`) to update the UI with the parsed weather data.

---

Development & Interaction

### How to Run

1. Project Setup:
   - Ensure dependencies (e.g., OkHttp, Gson, AndroidX libraries) are configured in the `build.gradle` files.
   - API keys (WeatherAPI, AI API) should be correctly set in respective classes for proper functionality.

2. Launching the Application:
   - The app starts with the `Loading_page`, which transitions to the `sign_in_page` after a 3-second animation.
   - Core actions like login, sign-up, onboarding, main menu navigation via RecyclerView, and bottom navbar functionality should be thoroughly tested in this phase.

3. Testing Notifications:
   - Notifications are automatically added to `NotificationManager` and displayed in `activity_notification`.
   - Use simulated API responses to test notification types and UI behavior.

4. Network Requests & API Integration:
   - Ensure network connectivity for testing API endpoints (Weather and AI vehicle analysis).
   - Verify proper handling of network errors and asynchronous responses.

---

Future Enhancements

- User Profile Implementation:
  - User profile management features (data display, editing) are in the planning phase.
  
- AI Implementation Enhancements:
  - Dynamic display of AI analysis data within the RecyclerView will be expanded.
  - Additional actions will be added to the UI for a more intuitive experience.

- Authentication:
  - Mockup sign-in will be replaced with a fully functional authentication system (e.g., Firebase Authentication, OAuth2).

- Navigation & Data Passing Enhancements:
  - Ensure that vehicle data is correctly passed to `activity_vehicle_info_analysis` and other related fragments via Intents.
  - Refining bottom navigation bar to include intuitive navigation with actionable icons for accessing key app features (booking, notifications, vehicle analysis).

