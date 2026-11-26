# 🌱 GreenGuardian AI
### AI-Powered Waste Classification & Reuse Platform

GreenGuardian AI is an intelligent environmental mobile application designed to help people identify waste materials, discover creative reuse opportunities, learn safe waste-handling practices, and find nearby recycling facilities.

The application combines **custom-trained Artificial Intelligence models, mobile application development, database management, and internet-based information retrieval** to encourage sustainable waste management.

> **AI Development Policy:** Both AI models will be developed and trained as part of this project. No pretrained model weights or LLM APIs will be used.

---

## 📌 Table of Contents

- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [AI Models](#-artificial-intelligence-models)
- [Waste Categories](#-waste-classification-categories)
- [Technology Stack](#-technology-stack)
- [System Architecture](#-system-architecture)
- [Application Screens](#-application-screens)
- [Database Design](#-database-design)
- [Backend API Structure](#-backend-api-structure)
- [Project Structure](#-project-structure)
- [Installation and Setup](#-installation-and-setup)
- [Model Training](#-model-training)
- [External API Configuration](#-external-api-configuration)
- [Security](#-security)
- [Development Roadmap](#-development-roadmap)
- [Limitations](#-limitations)
- [Future Improvements](#-future-improvements)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🌍 Project Overview

Waste management is a major environmental challenge. Many people do not know whether an item can be recycled, how it can be reused, or where it should be disposed of.

GreenGuardian AI aims to solve this problem through a mobile application that provides:

1. AI-based waste identification.
2. Personalized reuse ideas.
3. Step-by-step DIY project instructions.
4. Environmental information retrieval.
5. YouTube tutorial discovery.
6. Nearby recycling facility discovery.
7. Personal project and environmental progress tracking.

The application is designed for Android and iOS using Flutter.

### Project Goals

- Make waste identification easier.
- Encourage reuse instead of unnecessary disposal.
- Provide accessible environmental information.
- Promote safe waste handling.
- Connect users with recycling resources.
- Demonstrate practical custom AI model development.

---

## ✨ Key Features

### 1. 🔐 User Authentication

- User registration and login.
- Secure password hashing.
- JWT-based authentication.
- Profile management.
- Protected user-specific resources.

### 2. 📸 AI Waste Scanner

Users can capture a photo or upload an image from their gallery.

The custom CNN model will:

- Process the uploaded image.
- Predict the waste category.
- Return a confidence score.
- Display the classification result.
- Handle uncertain predictions.

### 3. ♻️ Smart Reuse Recommendations

After identifying waste, the application displays relevant reuse ideas.

Each recommendation can include:

- Project title.
- Required materials.
- Difficulty level.
- Estimated completion time.
- Step-by-step instructions.
- Safety precautions.

### 4. 🔎 Environmental Search Assistant

Users can search for environmental information through a text-based search interface.

Example queries:

- How can I reuse plastic bottles?
- What can I make from cardboard?
- How should old batteries be disposed of?
- What are the benefits of recycling metal?

**How it works:**

1. User submits a text query.
2. The custom NLP model classifies its intent or topic.
3. The retrieval system searches the local environmental knowledge database.
4. Relevant information is ranked and retrieved.
5. The backend searches external websites when required.
6. YouTube search provides relevant tutorial videos.
7. The application displays retrieved information with source links.

This is a **retrieval-based assistant**, not an LLM chatbot. It will use predefined response templates and retrieved content rather than generating unrestricted answers.

### 5. 🎥 YouTube Tutorial Discovery

- Search for relevant DIY videos.
- Display video titles and channel names.
- Display thumbnails where available.
- Open videos through YouTube links.

### 6. 🛠️ DIY Project Customizer

Users can filter projects based on:

- Available materials.
- Budget.
- Difficulty level.
- Estimated time.

The recommendation engine will return suitable projects from the application's stored catalogue.

### 7. ⚠️ Waste Safety Checker

Provides safety information for potentially hazardous materials.

Examples include:

- Batteries.
- Electronic waste.
- Broken glass.
- Chemical containers.
- Unknown plastic materials.

The application will prioritize safe handling and appropriate disposal. It will not recommend potentially dangerous DIY activities.

### 8. 📍 Recycling Center Locator

- Request location permission from the user.
- Search for nearby recycling facilities.
- Display available facility names and locations.
- Show directions through a map application.
- Display accepted materials and contact information when reliable data is available.

The locator will use available OpenStreetMap-based services. Facility information will be treated as potentially incomplete and will not be presented as guaranteed.

### 9. 📂 My Projects

Users can:

- Create projects.
- View saved projects.
- Edit project details.
- Delete projects.
- Track project completion.

### 10. 📊 Environmental Impact Tracker

Display user activity such as:

- Completed DIY projects.
- Estimated materials reused.
- Monthly activity.
- Environmental progress.
- Achievement points.

Environmental impact calculations will only use documented conversion factors. Estimates will be clearly labeled as estimates.

### 11. 👥 Community Showcase

A simple community feature where users can:

- Publish completed DIY projects.
- Upload project images.
- Add descriptions and materials used.
- Browse other users' projects.
- Like and bookmark posts.
- Report inappropriate content.

### 12. 📜 Scan History

Users can view previous scans, including:

- Waste category.
- Prediction confidence.
- Scan date.
- Related reuse ideas.

---

## 🤖 Artificial Intelligence Models

GreenGuardian AI will contain two independently developed AI components.

### Model 1: Custom CNN – Waste Image Classification

**Purpose:** Identify waste categories from images.

**Framework:** TensorFlow and Keras  
**Deployment:** TensorFlow Lite  
**Training:** Google Colab  
**Model Type:** Custom Convolutional Neural Network

#### Proposed Architecture

| Layer              | Configuration                   |
| ------------------ | ------------------------------- |
| Input              | 224 × 224 RGB image             |
| Conv Block 1       | 32 filters + ReLU + MaxPooling  |
| Conv Block 2       | 64 filters + ReLU + MaxPooling  |
| Conv Block 3       | 128 filters + ReLU + MaxPooling |
| Conv Block 4       | 256 filters + ReLU + MaxPooling |
| Feature Extraction | Global Average Pooling          |
| Regularization     | Dropout                         |
| Output             | Dense + Softmax (10 classes)    |

This is the initial architecture proposal. It may be adjusted based on training performance and available hardware.

#### Dataset

Primary dataset candidate:

[Garbage Classification Dataset – Kaggle](https://www.kaggle.com/datasets/mostafaabla/garbage-classification)

Additional references:

- [TrashNet Dataset](https://github.com/garythung/trashnet)
- [TACO Dataset](https://github.com/pedropro/TACO)

Dataset licensing, class availability, image quality, duplicate images, and class balance will be checked before training.

#### Training Process

1. Download and inspect the dataset.
2. Organize images into target categories.
3. Merge relevant glass subcategories.
4. Prepare training, validation, and testing splits.
5. Resize images to 224 × 224.
6. Apply data augmentation.
7. Build the CNN architecture from scratch.
8. Train the model.
9. Evaluate performance.
10. Export the trained model to TensorFlow Lite.

#### Evaluation Metrics

- Accuracy.
- Precision.
- Recall.
- F1-score.
- Confusion matrix.
- Training and validation loss.
- Training and validation accuracy.

A confidence threshold will be used to identify uncertain predictions. High confidence will not be treated as proof that a prediction is correct.

---

### Model 2: Custom NLP – Query Classification and Retrieval

**Purpose:** Understand the topic or intent of environmental queries and retrieve relevant information.

**Framework:** Scikit-learn  
**Proposed Model:** TF-IDF + Logistic Regression  
**Retrieval:** TF-IDF similarity or BM25

#### Example Query Categories

| Intent                 | Example                             |
| ---------------------- | ----------------------------------- |
| Reuse Ideas            | How can I reuse plastic bottles?    |
| Recycling              | Where can I recycle paper?          |
| Material Information   | What is cardboard made of?          |
| Safety                 | Are old batteries dangerous?        |
| DIY Instructions       | How can I make a flower pot?        |
| Environmental Benefits | Why is recycling important?         |
| Disposal Guidance      | How should broken glass be handled? |

#### NLP Workflow

1. Prepare a labeled dataset of environmental queries.
2. Clean and preprocess text.
3. Convert text into TF-IDF features.
4. Train a Logistic Regression classifier.
5. Evaluate the classifier on unseen test queries.
6. Store environmental knowledge documents.
7. Retrieve relevant documents using similarity ranking.
8. Compose a structured response using templates.
9. Attach source links where available.

The classifier will be trained using a project-specific labeled query dataset. The retrieval knowledge base will be maintained separately.

**Important:** This model will not generate unrestricted conversational responses. It will classify queries and retrieve relevant content.

---

## ♻️ Waste Classification Categories

The initial target is ten categories:

| #   | Category      | Example Items              |
| --- | ------------- | -------------------------- |
| 1   | Paper         | Newspaper, office paper    |
| 2   | Cardboard     | Packaging boxes            |
| 3   | Organic Waste | Fruit and vegetable waste  |
| 4   | Metal         | Cans, metal containers     |
| 5   | Plastic       | Bottles, plastic packaging |
| 6   | Glass         | Glass bottles and jars     |
| 7   | Clothes       | Old clothing, fabric       |
| 8   | Shoes         | Used footwear              |
| 9   | Batteries     | Household batteries        |
| 10  | Mixed Trash   | Non-separated waste        |

Actual model support for each category will depend on the verified training data. Unsupported or uncertain inputs will be handled separately rather than forced into an incorrect category.

---

## 🧰 Technology Stack

| Layer                 | Technology                              |
| --------------------- | --------------------------------------- |
| Mobile Application    | Flutter                                 |
| Programming Language  | Dart                                    |
| Backend               | Python                                  |
| Backend Framework     | Flask                                   |
| Database              | MongoDB                                 |
| Database Driver       | PyMongo                                 |
| Image Model           | TensorFlow / Keras                      |
| Mobile Model          | TensorFlow Lite                         |
| NLP Model             | Scikit-learn                            |
| NLP Vectorization     | TF-IDF                                  |
| NLP Classification    | Logistic Regression                     |
| Information Retrieval | TF-IDF / BM25                           |
| Web Search            | Brave Search API                        |
| Video Search          | YouTube Data API v3                     |
| Map Data              | OpenStreetMap                           |
| Authentication        | JWT                                     |
| Password Security     | Werkzeug password hashing or equivalent |
| API Testing           | Postman                                 |
| IDE                   | VS Code                                 |
| Android Development   | Android Studio                          |
| Model Training        | Google Colab                            |
| Version Control       | Git and GitHub                          |

---

## 🏗️ System Architecture

```text
                   Flutter Mobile App
                           |
             +-------------+-------------+
             |                           |
       Local CNN Model               Flask Backend
        (TensorFlow Lite)                 |
             |                 +----------+----------+
       Waste Prediction        |          |          |
             |                |          |          |
       Reuse Suggestions    Auth/API   NLP Model   External APIs
                              |          |          |
                              |      Knowledge     +-- Brave Search
                              |      Retrieval     +-- YouTube API
                              |          |
                              +----------+
                                    |
                                 MongoDB
                                    |
                   +----------------+----------------+
                   |                |                |
                 Users            Projects         Scans
                   |                |                |
              Community         Progress        Knowledge Base
```

### Application Flow

**Waste Scanning:**

1. User selects or captures an image.
2. Flutter preprocesses the image.
3. TensorFlow Lite runs the custom CNN.
4. The application receives a predicted category and confidence.
5. Flutter requests related recommendations from Flask.
6. Flask retrieves relevant data from MongoDB.
7. The user can save a selected idea as a project.

**Environmental Search:**

1. User enters a query.
2. Flutter sends the query to Flask.
3. The NLP classifier identifies the topic or intent.
4. The retrieval engine searches the local knowledge base.
5. External search services provide additional results.
6. The backend ranks and returns the results.
7. Flutter displays the information, websites, and video links.

---

## 📱 Application Screens

The planned application contains the following screens:

| #   | Screen              | Purpose                                 |
| --- | ------------------- | --------------------------------------- |
| 1   | Splash Screen       | Application branding                    |
| 2   | Onboarding          | Introduce the application               |
| 3   | Sign Up             | Create an account                       |
| 4   | Login               | Authenticate users                      |
| 5   | Home Dashboard      | Main application overview               |
| 6   | Waste Scanner       | Capture or upload waste images          |
| 7   | Image Preview       | Review selected image                   |
| 8   | AI Analysis         | Display analysis state                  |
| 9   | Detection Results   | Show category and confidence            |
| 10  | Reuse Ideas         | Display recommendations                 |
| 11  | Product Details     | View reuse idea details                 |
| 12  | DIY Instructions    | Display project steps                   |
| 13  | My Projects         | Manage saved projects                   |
| 14  | Project Details     | View individual project                 |
| 15  | Scan History        | View previous scans                     |
| 16  | Eco Challenges      | Display simple environmental challenges |
| 17  | Eco Progress        | Show user progress                      |
| 18  | Profile             | Manage account information              |
| 19  | Settings            | Manage application settings             |
| 20  | About & Help        | Application information                 |
| 21  | AI Search Assistant | Enter environmental queries             |
| 22  | Search Results      | Display knowledge, websites, and videos |
| 23  | DIY Customizer      | Filter projects by user preferences     |
| 24  | Safety Checker      | Display waste safety guidance           |
| 25  | Recycling Locator   | Discover nearby recycling facilities    |
| 26  | Community Showcase  | Browse and share projects               |

Some screens may share reusable UI components. The final implementation will prioritize functional core workflows over simply completing a screen count.

---

## 🗄️ Database Design

MongoDB will store application and user information.

### Proposed Collections

| Collection              | Purpose                         |
| ----------------------- | ------------------------------- |
| users                   | Account and profile information |
| scans                   | Waste scanning history          |
| reuse_ideas             | Reuse recommendations           |
| projects                | User-created and saved projects |
| challenges              | Environmental challenges        |
| user_achievements       | User progress and achievements  |
| environmental_knowledge | NLP retrieval documents         |
| community_posts         | Community project posts         |
| feedback                | User feedback and reports       |

### Example User Document

```json
{
  "_id": "ObjectId",
  "name": "Example User",
  "email": "user@example.com",
  "password_hash": "hashed_password",
  "created_at": "timestamp"
}
```

### Example Scan Document

```json
{
  "_id": "ObjectId",
  "user_id": "ObjectId",
  "category": "Plastic",
  "confidence": 0.92,
  "image_reference": "stored_image_reference",
  "created_at": "timestamp"
}
```

### CRUD Operations

The application will support:

- Create: Save projects, scans, and community posts.
- Read: Retrieve profiles, scan history, and saved projects.
- Update: Edit profile and project information.
- Delete: Remove saved projects and supported user content.

All user-owned records will be protected by backend authorization checks.

---

## 🔌 Backend API Structure

The following is the planned API design. Routes may be adjusted during implementation.

### Authentication

| Method | Endpoint             | Purpose                                                |
| ------ | -------------------- | ------------------------------------------------------ |
| POST   | `/api/auth/register` | Register user                                          |
| POST   | `/api/auth/login`    | Login user                                             |
| GET    | `/api/auth/me`       | Get authenticated user                                 |
| POST   | `/api/auth/logout`   | Handle client logout/token invalidation if implemented |

### User Profile

| Method | Endpoint             | Purpose        |
| ------ | -------------------- | -------------- |
| GET    | `/api/users/profile` | Get profile    |
| PUT    | `/api/users/profile` | Update profile |

### Waste Scans

| Method | Endpoint         | Purpose            |
| ------ | ---------------- | ------------------ |
| POST   | `/api/scans`     | Save scan result   |
| GET    | `/api/scans`     | Get scan history   |
| GET    | `/api/scans/:id` | Get scan details   |
| DELETE | `/api/scans/:id` | Delete scan record |

### Reuse Ideas

| Method | Endpoint                        | Purpose                  |
| ------ | ------------------------------- | ------------------------ |
| GET    | `/api/ideas`                    | Get reuse ideas          |
| GET    | `/api/ideas/:id`                | Get idea details         |
| GET    | `/api/ideas/category/:category` | Get category-based ideas |

### Projects

| Method | Endpoint            | Purpose             |
| ------ | ------------------- | ------------------- |
| POST   | `/api/projects`     | Create project      |
| GET    | `/api/projects`     | Get user projects   |
| GET    | `/api/projects/:id` | Get project details |
| PUT    | `/api/projects/:id` | Update project      |
| DELETE | `/api/projects/:id` | Delete project      |

### Search Assistant

| Method | Endpoint             | Purpose                                 |
| ------ | -------------------- | --------------------------------------- |
| POST   | `/api/search/ask`    | Classify query and retrieve information |
| GET    | `/api/search/web`    | Search external websites                |
| GET    | `/api/search/videos` | Search YouTube videos                   |

### Additional Features

| Method | Endpoint                          | Purpose                    |
| ------ | --------------------------------- | -------------------------- |
| GET    | `/api/safety/:category`           | Get safety guidance        |
| GET    | `/api/locator`                    | Find recycling facilities  |
| GET    | `/api/impact/summary`             | Get environmental progress |
| POST   | `/api/community/posts`            | Publish a community post   |
| GET    | `/api/community/posts`            | Browse community posts     |
| POST   | `/api/community/posts/:id/like`   | Like a post                |
| POST   | `/api/community/posts/:id/report` | Report a post              |

---

## 📁 Project Structure

The repository will use a separate frontend, backend, and AI model directory.

```text
GreenGuardian-AI/
│
├── frontend/
│   └── lib/
│       ├── core/
│       │   ├── constants/
│       │   ├── theme/
│       │   ├── routes/
│       │   └── utils/
│       │
│       ├── models/
│       ├── services/
│       ├── providers/
│       │
│       ├── features/
│       │   ├── authentication/
│       │   ├── home/
│       │   ├── scanner/
│       │   ├── recommendations/
│       │   ├── search/
│       │   ├── projects/
│       │   ├── safety/
│       │   ├── locator/
│       │   ├── community/
│       │   ├── progress/
│       │   └── profile/
│       │
│       ├── shared/
│       │   ├── widgets/
│       │   └── components/
│       │
│       └── main.dart
│
├── backend/
│   ├── app/
│   │   ├── routes/
│   │   │   ├── auth_routes.py
│   │   │   ├── user_routes.py
│   │   │   ├── scan_routes.py
│   │   │   ├── project_routes.py
│   │   │   ├── search_routes.py
│   │   │   ├── youtube_routes.py
│   │   │   ├── safety_routes.py
│   │   │   ├── locator_routes.py
│   │   │   └── community_routes.py
│   │   │
│   │   ├── services/
│   │   │   ├── knowledge_engine.py
│   │   │   ├── web_search_service.py
│   │   │   ├── youtube_service.py
│   │   │   ├── safety_service.py
│   │   │   └── impact_calculator.py
│   │   │
│   │   ├── database/
│   │   │   └── mongodb.py
│   │   │
│   │   ├── middleware/
│   │   ├── utils/
│   │   └── __init__.py
│   │
│   ├── config.py
│   ├── requirements.txt
│   ├── run.py
│   └── .env.example
│
├── ai-models/
│   ├── waste-classifier/
│   │   ├── dataset/
│   │   ├── notebooks/
│   │   ├── training/
│   │   ├── evaluation/
│   │   └── exports/
│   │
│   └── nlp-classifier/
│       ├── dataset/
│       ├── notebooks/
│       ├── training/
│       ├── evaluation/
│       └── exports/
│
├── knowledge-base/
│   ├── environmental_knowledge.json
│   ├── reuse_ideas.json
│   └── safety_rules.json
│
├── docs/
│   ├── project-proposal.md
│   ├── architecture.md
│   └── api-documentation.md
│
├── .gitignore
└── README.md
```

Large datasets, trained model artifacts, credentials, and private user uploads should not be committed to GitHub unnecessarily. Document how to obtain or generate them.

---

## ⚙️ Installation and Setup

### Prerequisites

Install the following tools:

- Flutter SDK
- Dart SDK
- Python
- MongoDB Community Server
- MongoDB Compass (optional GUI)
- Android Studio
- Visual Studio Code
- Git
- Postman (for API testing)

Google Colab can be used for model training.

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/GreenGuardian-AI.git

cd GreenGuardian-AI
```

### 2. Backend Setup

Open the backend directory:

```bash
cd backend
```

Create a virtual environment:

```bash
python -m venv venv
```

Activate it on Windows PowerShell:

```powershell
.\venv\Scripts\Activate.ps1
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Create a `.env` file using `.env.example` and configure the required settings.

Start the backend:

```bash
python run.py
```

The backend development server will run at the configured local address and port.

### 3. Flutter Setup

Open another terminal:

```bash
cd frontend
```

Install Flutter dependencies:

```bash
flutter pub get
```

Check the development environment:

```bash
flutter doctor
```

Run the application:

```bash
flutter run
```

### 4. MongoDB Setup

1. Install MongoDB Community Server.
2. Start the MongoDB service.
3. Create or select the project database.
4. Configure the MongoDB connection string in the backend environment file.
5. Verify the connection through the backend.

---

## 🧠 Model Training

### CNN Training

The CNN will be trained in Google Colab.

Suggested initial configuration:

| Parameter         | Proposed Value           |
| ----------------- | ------------------------ |
| Input Image Size  | 224 × 224                |
| Color Channels    | RGB                      |
| Target Classes    | 10                       |
| Data Split        | 70% / 15% / 15%          |
| Optimizer         | Adam                     |
| Activation        | ReLU                     |
| Output Activation | Softmax                  |
| Loss              | Categorical Crossentropy |

The split and hyperparameters may be adjusted after dataset inspection and experimentation.

After training:

1. Evaluate the model.
2. Save the trained Keras model.
3. Convert it to TensorFlow Lite.
4. Test the converted model.
5. Integrate the model into Flutter.

### NLP Training

The NLP model will be trained using a labeled environmental-query dataset.

Proposed workflow:

1. Create query examples for each intent.
2. Clean and normalize text.
3. Split the dataset into training and testing sets.
4. Extract TF-IDF features.
5. Train Logistic Regression.
6. Evaluate classification performance.
7. Save the classifier and vectorizer.
8. Integrate them into the Flask backend.
9. Test retrieval quality using sample queries.

The NLP model and knowledge base are separate components: the classifier identifies the query category, while the retrieval system finds relevant documents.

---

## 🌐 External API Configuration

External services will be called from the Flask backend. API keys must never be placed directly in Flutter source code or committed to GitHub.

### Brave Search API

Used to retrieve relevant web pages.

Documentation:

https://api-dashboard.search.brave.com/documentation

The application will use the standard Web Search functionality, not LLM-based answers or AI-generated response endpoints.

### YouTube Data API v3

Used to search for relevant tutorial videos.

Documentation:

https://developers.google.com/youtube/v3/docs/search/list

The application will retrieve available video metadata and construct valid YouTube links using returned video IDs.

### OpenStreetMap

Used as the basis for discovering nearby recycling locations.

Useful services:

- https://www.openstreetmap.org/
- https://nominatim.org/
- https://overpass-api.de/

Usage policies, attribution requirements, service limits, and permitted use must be checked before deployment. The application should avoid excessive requests and cache suitable results.

---

## 🔒 Security

Security measures include:

- Store passwords as secure hashes, never as plain text.
- Use JWT authentication for protected routes.
- Validate all incoming requests.
- Check record ownership before update and delete operations.
- Keep API credentials in environment variables.
- Do not expose database credentials to the mobile app.
- Restrict and validate uploaded image files.
- Handle location permission responsibly.
- Provide reporting and moderation controls for community content.

Example environment configuration:

```env
FLASK_ENV=development
SECRET_KEY=your_secret_key
JWT_SECRET_KEY=your_jwt_secret

MONGO_URI=mongodb://localhost:27017/
DATABASE_NAME=greenguardian

BRAVE_SEARCH_API_KEY=your_brave_api_key
YOUTUBE_API_KEY=your_youtube_api_key
```

Never commit the real `.env` file.

---

## 🗓️ Development Roadmap

| Phase    | Tasks                                                      |
| -------- | ---------------------------------------------------------- |
| Phase 1  | Project setup, requirements, UI planning                   |
| Phase 2  | Dataset preparation and CNN training                       |
| Phase 3  | Flutter authentication and dashboard                       |
| Phase 4  | Scanner and TensorFlow Lite integration                    |
| Phase 5  | Flask backend and MongoDB integration                      |
| Phase 6  | Reuse catalogue and DIY project CRUD                       |
| Phase 7  | NLP classifier and retrieval system                        |
| Phase 8  | Brave Search and YouTube integration                       |
| Phase 9  | Safety checker and recycling locator                       |
| Phase 10 | Community showcase and progress tracking                   |
| Phase 11 | Testing, bug fixing, documentation, and final presentation |

**Development priority:** Complete authentication, scanning, CNN integration, reuse recommendations, backend CRUD, and NLP retrieval before spending significant time on optional community and progress features.

---

## ⚠️ Limitations

- The CNN may make incorrect predictions on unfamiliar images.
- Its performance depends on dataset quality and real-world image diversity.
- The NLP model will only support the intents and vocabulary represented in its training data.
- Search results depend on external service availability and API quotas.
- Recycling facility data may be incomplete or outdated.
- Some features require an active internet connection.
- iOS build and simulator testing require macOS and Xcode.
- Environmental impact values are estimates and depend on documented calculation factors.

---

## 🚀 Future Improvements

Possible future enhancements include:

- Voice-based environmental search.
- More waste classification categories.
- Multilingual support.
- Improved personalization based on user activity.
- More detailed recycling facility information.
- Advanced environmental analytics.
- Community challenges and events.
- Improved image classification with a larger and more diverse dataset.
- Offline access to selected knowledge and DIY guides.

---

## 🤝 Contributing

Contributions and suggestions are welcome.

1. Fork the repository.
2. Create a feature branch.
3. Make your changes.
4. Test the changes.
5. Submit a pull request.

---

## 📄 License

A suitable open-source license will be selected before public distribution. Dataset licenses and third-party API terms must be respected separately.

---

## 🌱 Project Vision

**Identify Waste. Discover Reuse. Protect the Environment.**

GreenGuardian AI aims to make sustainable choices easier through practical mobile technology and custom Artificial Intelligence.
