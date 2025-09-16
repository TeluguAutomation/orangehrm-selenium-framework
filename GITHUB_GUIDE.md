# 🚀 GitHub Guide for OrangeHRM Selenium Framework

## Table of Contents
1. [Initial Repository Setup](#initial-repository-setup)
2. [Authentication Methods](#authentication-methods)
3. [Cloning a Repository](#cloning-a-repository)
4. [Daily Workflow - Pull & Push](#daily-workflow---pull--push)
5. [Troubleshooting](#troubleshooting)
6. [Best Practices](#best-practices)

---

## 🆕 Initial Repository Setup

### Step 1: Create GitHub Repository
1. Go to [GitHub.com](https://github.com)
2. Click **"New repository"** or **"+"** → **"New repository"**
3. Fill in repository details:
   - **Repository name**: `orangehrm-selenium-framework`
   - **Description**: `OrangeHRM Selenium Automation Framework with Page Object Model`
   - **Visibility**: Public (or Private)
   - **Initialize**: ❌ Don't check "Add a README file"
4. Click **"Create repository"**

### Step 2: Configure Git Identity

# Set your Git username and email (one-time setup)
git config --global user.name "YourUsername"
git config --global user.email "your.email@example.com"

# Verify configuration
git config --list


### Step 3: Initialize Local Repository

# Navigate to your project directory
cd C:\Users\bhask\eclipse-workspace\OrangeHRM_APP

# Initialize Git repository
git init

# Add remote origin
git remote add origin https://github.com/YourUsername/orangehrm-selenium-framework.git

# Verify remote
git remote -v


### Step 4: Create .gitignore File

# Create .gitignore file
echo "# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# Eclipse IDE
.settings/
.project
.classpath
.metadata/
bin/
tmp/
*.tmp
*.bak
*.swp
*~.nib
local.properties

# IntelliJ IDEA
.idea/
*.iml
*.ipr
*.iws
out/

# VS Code
.vscode/

# OS generated files
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
ehthumbs.db
Thumbs.db

# Logs
*.log
logs/

# Compiled class files
*.class

# Package Files
*.jar
*.war
*.ear
*.zip
*.tar.gz
*.rar

# Test Reports
test-output/
surefire-reports/

# Screenshots
target/screenshots/

# Other
*.log
*.temp
*.tmp
*.bak
*.swp
*~
.#*" > .gitignore


### Step 5: First Commit and Push

# Add all files to staging
git add .

# Check what will be committed
git status

# Create initial commit
git commit -m "Initial commit: OrangeHRM Selenium Framework with Page Object Model

- Complete Page Object Model implementation
- LoginPage, DashboardPage, LogoutPage with comprehensive methods
- BaseTest with TestNG lifecycle management
- CommonMethods utility class with 50+ reusable methods
- TestConfig for centralized configuration management
- TestNG XML configuration for Chrome browser testing
- 11 test cases covering Login, Dashboard, and Logout functionality
- Maven project structure with Selenium, TestNG, WebDriverManager dependencies
- Clean architecture following OOP principles and design patterns"

# Push to GitHub (first time)
git push -u origin master


---

## 🔐 Authentication Methods

### Method 1: Browser Authentication (Recommended)

# When prompted for authentication, a browser window will open
git push -u origin master
# → Browser opens → Sign in to GitHub → Authorize


### Method 2: Personal Access Token
1. Go to GitHub → Settings → Developer settings → Personal access tokens
2. Click **"Generate new token"** → **"Generate new token (classic)"**
3. Fill in details:
   - **Note**: `OrangeHRM Framework Access`
   - **Expiration**: `90 days` (or your preference)
   - **Scopes**: Check `repo` (Full control of private repositories)
4. Click **"Generate token"**
5. **Copy the token** (you won't see it again!)


# Use token as password when prompted
git push -u origin master
# Username: YourGitHubUsername
# Password: [Paste your token here]


### Method 3: SSH Key (Advanced)

# Generate SSH key
ssh-keygen -t rsa -b 4096 -C "your.email@example.com"

# Add SSH key to GitHub
# Copy public key: cat ~/.ssh/id_rsa.pub
# Add to GitHub → Settings → SSH and GPG keys

# Use SSH URL instead of HTTPS
git remote set-url origin git@github.com:YourUsername/orangehrm-selenium-framework.git


---

## 📥 Cloning a Repository

### Clone to New Location

# Navigate to desired parent directory
cd C:\Users\bhask\eclipse-workspace

# Clone the repository
git clone https://github.com/YourUsername/orangehrm-selenium-framework.git

# Navigate to cloned project
cd orangehrm-selenium-framework

# Verify files are present
ls
# or
dir


### Clone Specific Branch

# Clone specific branch
git clone -b feature-branch https://github.com/YourUsername/orangehrm-selenium-framework.git

# Clone with different folder name
git clone https://github.com/YourUsername/orangehrm-selenium-framework.git my-framework


### Verify Clone Success

# Check project structure
tree /f
# or
ls -la

# Check Git status
git status

# Check commit history
git log --oneline

# Test Maven build
mvn clean compile


---

## 🔄 Daily Workflow - Pull & Push

### Before Starting Work (Always Pull First!)

# Navigate to project directory
cd C:\Users\bhask\eclipse-workspace\OrangeHRM_APP

# Check current status
git status

# Pull latest changes from GitHub
git pull origin master

# Verify you're up to date
git log --oneline -5


### Making Changes and Pushing

# 1. Make your code changes
# 2. Check what files changed
git status

# 3. Add specific files
git add src/main/java/com/OrangeHRM/UI/pageObjects/NewPage.java
git add src/test/java/com/OrangeHRM/UITest/testCases/NewTest.java

# OR add all changes
git add .

# 4. Check what will be committed
git status

# 5. Commit with descriptive message
git commit -m "Add new test case for user management functionality

- Created UserManagementPage with locators and methods
- Added UserManagementTest with 5 test cases
- Updated testng.xml to include new test class
- Fixed timeout issue in CommonMethods.waitForElementToBeClickable()"

# 6. Push to GitHub
git push origin master


### Working with Branches (Advanced)

# Create new branch for feature
git checkout -b feature/user-management

# Make changes and commit
git add .
git commit -m "Add user management feature"

# Push new branch to GitHub
git push -u origin feature/user-management

# Switch back to master
git checkout master

# Merge feature branch
git merge feature/user-management

# Push merged changes
git push origin master

# Delete local branch
git branch -d feature/user-management


---

## 🔧 Troubleshooting

### Authentication Issues

# Clear stored credentials
git config --global --unset credential.helper
git config --global credential.helper manager-core

# Or use token authentication
git remote set-url origin https://YourUsername:YOUR_TOKEN@github.com/YourUsername/orangehrm-selenium-framework.git


### Merge Conflicts

# When pull shows conflicts
git pull origin master
# → Shows conflict files

# Edit conflicted files manually
# Remove conflict markers: <<<<<<< HEAD, =======, >>>>>>> branch

# Add resolved files
git add resolved-file.java

# Complete merge
git commit -m "Resolve merge conflicts in LoginPage.java"


### Undo Changes

# Undo uncommitted changes
git checkout -- filename.java

# Undo all uncommitted changes
git reset --hard HEAD

# Undo last commit (keep changes)
git reset --soft HEAD~1

# Undo last commit (discard changes)
git reset --hard HEAD~1


### Check Repository Status

# Check remote URL
git remote -v

# Check branch information
git branch -a

# Check commit history
git log --oneline --graph

# Check file differences
git diff
git diff --staged


---

## 📋 Best Practices

### Commit Messages

# Good commit messages
git commit -m "Add login validation test case"
git commit -m "Fix WebDriverWait timeout issue in CommonMethods"
git commit -m "Update testng.xml to include Dashboard tests"

# Bad commit messages
git commit -m "fix"
git commit -m "changes"
git commit -m "update"


### File Management

# Always check what you're adding
git add -p  # Interactive add (review each change)

# Check status before committing
git status
git diff --staged

# Use .gitignore effectively
# Never commit:
# - IDE files (.settings/, .idea/)
# - Build artifacts (target/, bin/)
# - Log files (*.log)
# - Temporary files (*.tmp, *.bak)


### Regular Maintenance

# Daily routine
git pull origin master    # Get latest changes
# Make changes
git add .
git commit -m "Descriptive message"
git push origin master

# Weekly routine
git log --oneline -10     # Review recent commits
git status               # Check for uncommitted changes
git branch -a            # Check all branches


---

## 🎯 Quick Reference Commands

### Essential Commands

# Setup
git init
git remote add origin <URL>
git config user.name "YourName"
git config user.email "your@email.com"

# Daily workflow
git pull origin master
git add .
git commit -m "Message"
git push origin master

# Information
git status
git log --oneline
git remote -v
git branch -a

# Troubleshooting
git reset --hard HEAD
git checkout -- filename
git pull --rebase origin master


### Maven Integration

# Build and test
mvn clean compile
mvn test
mvn clean test -DsuiteXmlFile=testng.xml

# Run specific test class
mvn test -Dtest=LoginTest
mvn test -Dtest=DashboardTest


---

## 📞 Support

### Common Issues
1. **"Authentication failed"** → Use Personal Access Token
2. **"Repository not found"** → Check URL and permissions
3. **"Merge conflicts"** → Resolve conflicts manually
4. **"Nothing to commit"** → Check if files are staged with `git status`

### Getting Help
- GitHub Documentation: https://docs.github.com
- Git Documentation: https://git-scm.com/doc
- Stack Overflow: https://stackoverflow.com/questions/tagged/git

---

**🎉 Happy Coding with GitHub! 🚀**

*This guide covers everything from initial setup to daily collaboration. Keep this document handy for reference!*
