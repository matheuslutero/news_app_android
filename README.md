# News App

An Android news application built with **Jetpack Compose** and **Clean Architecture** that fetches news articles from BBC News sources using the [News API](https://newsapi.org/).

## Getting Started

### Setup
1. Register for an API key at [News API](https://newsapi.org/register)
2. Create a `local.properties` file in the root directory:
```properties
API_KEY=YOUR_API_KEY_HERE
```
3. Build and run the project

### Build Variants
- **`news`** - General news from BBC News
- **`sports`** - Sports news from BBC Sport

## Tech Stack

- **UI**: Jetpack Compose, Material 3
- **Architecture**: MVVM, Clean Architecture
- **DI**: Dagger Hilt
- **Networking**: Retrofit, OkHttp
- **JSON**: Moshi
- **Navigation**: Navigation Compose
- **Authentication**: AndroidX Biometric
- **Coroutines**: Kotlin Coroutines & Flow

## Screenshots

|            General News            |                   Sport News                   |
|:----------------------------------:|:----------------------------------------------:|
| ![News](screenshots/news_app.jpeg) | ![Sport News](screenshots/sport_news_app.jpeg) |
