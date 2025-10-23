# 🐱 Cat Shelter App

A beautiful, modern Android app for managing cats in a shelter, built with Jetpack Compose and featuring a delightful baby pink theme.

## ✨ Features

### 🎨 **Beautiful UI Design**
- **Baby Pink Theme**: Soft, adorable color palette throughout the app
- **Material 3 Design**: Modern, accessible interface components
- **Smooth Animations**: Delightful transitions and micro-interactions
- **Responsive Layout**: Works perfectly on all screen sizes

### 🐱 **Cat Management**
- **Add New Cats**: Complete form with all essential cat information
- **Edit Cat Details**: Update any cat's information easily
- **Adoption Tracking**: Mark cats as adopted with date tracking
- **Medical Notes**: Keep track of health information
- **Breed & Color Info**: Detailed cat characteristics
- **Arrival Date**: Track when cats arrived at the shelter

### 📱 **User Experience**
- **Intuitive Navigation**: Easy-to-use interface
- **Search & Filter**: Find cats quickly
- **Empty States**: Helpful guidance when no cats are present
- **Confirmation Dialogs**: Prevent accidental deletions
- **Real-time Updates**: Instant UI updates when data changes

## 🏗️ **Architecture**

### **Modern Android Development**
- **Jetpack Compose**: Declarative UI framework
- **Room Database**: Local data persistence
- **MVVM Pattern**: Clean separation of concerns
- **Kotlin Coroutines**: Asynchronous programming
- **Material 3**: Latest design system

### **Project Structure**
```
app/src/main/java/com/vm3dir_mobilprog_catshelter/
├── data/
│   ├── Cat.kt                 # Cat data model
│   ├── CatDao.kt             # Database access object
│   └── CatDatabase.kt        # Room database setup
├── repository/
│   └── CatRepository.kt      # Data repository
├── viewmodel/
│   └── CatViewModel.kt       # UI state management
├── ui/
│   ├── components/
│   │   ├── CatCard.kt        # Cat display card
│   │   └── AddEditCatDialog.kt # Add/edit form
│   ├── screens/
│   │   └── CatListScreen.kt  # Main screen
│   └── theme/
│       ├── Color.kt          # Color definitions
│       ├── Theme.kt          # Material theme
│       └── Type.kt           # Typography
└── MainActivity.kt           # Main activity
```

## 🎨 **Design System**

### **Color Palette**
- **Primary Pink**: `#F8BBD9` - Main brand color
- **Light Pink**: `#FCE4EC` - Background color
- **Accent Pink**: `#F48FB1` - Interactive elements
- **Dark Pink**: `#E91E63` - Text and emphasis
- **Soft White**: `#FFF8FC` - Card backgrounds
- **Text Pink**: `#AD1457` - Primary text color

### **Typography**
- **Headlines**: Bold, prominent text for cat names
- **Body Text**: Readable text for descriptions
- **Captions**: Smaller text for details and metadata

## 🚀 **Getting Started**

### **Prerequisites**
- Android Studio Hedgehog or later
- Android SDK 24+ (Android 7.0)
- Kotlin 2.0+

### **Installation**
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator

### **Dependencies**
- **Room**: Local database
- **Compose**: UI framework
- **Navigation**: Screen navigation
- **ViewModel**: State management
- **Material 3**: Design system

## 📱 **Usage**

### **Adding a Cat**
1. Tap the pink "+" button
2. Fill in the cat's information:
   - Name (required)
   - Age in years (required)
   - Breed (required)
   - Color (required)
   - Description (optional)
   - Arrival date (auto-filled with today's date)
   - Medical notes (optional)
3. Tap "Add Cat" to save

### **Managing Cats**
- **Edit**: Tap on a cat card to reveal action buttons, then tap "Edit"
- **Adopt**: Tap "Adopt" to mark a cat as adopted
- **Make Available**: Tap "Available" to return an adopted cat to the shelter
- **Delete**: Tap "Delete" to permanently remove a cat from the shelter

### **Viewing Cats**
- **Available Cats**: Default view showing cats ready for adoption
- **Adopted Cats**: Tap the heart icon to view recently adopted cats
- **Cat Details**: Tap any cat card to see full information

## 🔧 **Technical Details**

### **Database Schema**
```sql
CREATE TABLE cats (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    breed TEXT NOT NULL,
    color TEXT NOT NULL,
    description TEXT,
    arrivalDate TEXT,
    medicalNotes TEXT,
    isAdopted INTEGER NOT NULL DEFAULT 0,
    adoptionDate TEXT
);
```

### **Key Components**
- **CatCard**: Displays cat information with action buttons
- **AddEditCatDialog**: Form for adding/editing cats
- **CatListScreen**: Main screen with cat list and navigation
- **CatViewModel**: Manages UI state and business logic

### **Data Flow**
1. User interacts with UI
2. ViewModel processes the action
3. Repository handles data operations
4. Room database persists changes
5. UI updates automatically via StateFlow

## 🎯 **Future Enhancements**

### **Planned Features**
- 📸 **Photo Upload**: Add cat photos
- 🔍 **Search & Filter**: Find cats by breed, age, color
- 📊 **Statistics**: Adoption rates and shelter metrics
- 📅 **Calendar**: Track appointments and checkups
- 🌐 **Cloud Sync**: Backup data to cloud
- 📱 **Widgets**: Home screen widgets
- 🔔 **Notifications**: Reminders for checkups

### **Advanced Features**
- 🏥 **Medical Records**: Detailed health tracking
- 👥 **Adopter Profiles**: Track who adopted which cats
- 📈 **Analytics**: Shelter performance metrics
- 🎨 **Themes**: Multiple color schemes
- 🌙 **Dark Mode**: Dark theme support

## 🤝 **Contributing**

We welcome contributions! Please feel free to:
- Report bugs
- Suggest new features
- Submit pull requests
- Improve documentation

## 📄 **License**

This project is open source and available under the MIT License.

## 🙏 **Acknowledgments**

- **Material Design**: For the beautiful design system
- **Jetpack Compose**: For the modern UI framework
- **Room**: For efficient local database management
- **Android Community**: For inspiration and best practices

---

**Made with ❤️ for cats and their caretakers** 🐱💕

