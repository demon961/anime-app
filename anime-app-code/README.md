# Apex Anime

A clean, modern Android application that provides access to anime content through a WebView interface with integrated ad management.

## Features

- **WebView Integration**: Loads anime content from hianime.to
- **Ad Blocking**: JavaScript injection to hide third-party advertisements
- **Google AdMob Integration**: Banner ads at the bottom of the screen
- **Responsive Design**: ConstraintLayout-based UI for optimal viewing
- **Progress Tracking**: Visual progress bar during page loading
- **Back Navigation**: Proper WebView history management
- **Lifecycle Management**: Proper WebView and AdMob lifecycle handling

## Technical Specifications

### Architecture
- Clean Architecture principles
- Single Activity design
- ViewBinding for view references
- Utility classes for WebView configuration

### Dependencies
- AndroidX AppCompat
- ConstraintLayout
- Google Play Services Ads (AdMob)
- Navigation Component
- Material Design Components

### Permissions
- `INTERNET`: Required for WebView functionality
- `ACCESS_NETWORK_STATE`: Required for AdMob

## Setup Instructions

1. **Clone the repository**
2. **Open in Android Studio**
3. **Sync Gradle dependencies**
4. **Build and run on device/emulator**

## Configuration

### AdMob Setup
The app uses test AdMob IDs by default:
- App ID: `ca-app-pub-3940256099942544~3347511713`
- Banner Ad Unit ID: `ca-app-pub-3940256099942544/6300978111`

For production, replace these with your actual AdMob IDs in:
- `AndroidManifest.xml` (App ID)
- `strings.xml` (Banner Ad Unit ID)

### WebView Configuration
- JavaScript enabled
- DOM storage enabled
- Mixed content allowed
- Custom user agent string

## Code Structure

```
app/src/main/java/com/example/apexanime/
├── MainActivity.kt                 # Main activity with WebView and AdMob
├── constants/
│   └── AdMobConstants.kt          # AdMob configuration constants
└── utils/
    └── WebViewConfigurator.kt     # WebView setup and ad blocking utilities
```

## Ad Blocking Features

The app includes JavaScript injection to hide common ad elements:
- iframe ads
- Google AdSense elements
- Popup modals
- Ad containers
- Various ad-related CSS classes and IDs

## Best Practices Implemented

- Explicit type declarations
- Proper lifecycle management
- Clean separation of concerns
- Consistent naming conventions
- Error handling
- Performance optimization

## Testing

The app includes:
- Unit test structure
- Instrumented test setup
- AdMob test device configuration

## License

This project is for educational and development purposes. 