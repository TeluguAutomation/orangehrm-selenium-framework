# 🚀 GitHub Quick Reference Card

## 🔄 Daily Workflow (Copy-Paste Ready)

### Before Starting Work

cd C:\Users\bhask\eclipse-workspace\OrangeHRM_APP
git status
git pull origin master


### After Making Changes

git add .
git status
git commit -m "Your descriptive message here"
git push origin master


---

## 🔐 Authentication Setup (One-Time)

### Method 1: Browser Auth (Easiest)

git push -u origin master
# → Browser opens → Sign in → Authorize


### Method 2: Personal Access Token
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate token with `repo` scope
3. Use token as password when prompted

---

## 📥 Clone Repository (New Machine)


# Navigate to desired location
cd C:\Users\bhask\eclipse-workspace

# Clone the repository
git clone https://github.com/TeluguAutomation/orangehrm-selenium-framework.git

# Navigate to project
cd orangehrm-selenium-framework

# Verify and build
ls
mvn clean compile


---

## 🛠️ Troubleshooting

### Authentication Failed

git config --global --unset credential.helper
git config --global credential.helper manager-core


### Merge Conflicts

git pull origin master
# Edit conflicted files
git add .
git commit -m "Resolve merge conflicts"


### Undo Changes

git checkout -- filename.java          # Undo specific file
git reset --hard HEAD                  # Undo all changes
git reset --soft HEAD~1               # Undo last commit (keep changes)


---

## 📋 Essential Commands

| Command | Purpose |
|---------|---------|
| `git status` | Check current status |
| `git add .` | Stage all changes |
| `git commit -m "message"` | Commit changes |
| `git push origin master` | Push to GitHub |
| `git pull origin master` | Pull from GitHub |
| `git log --oneline` | View commit history |
| `git remote -v` | Check remote URL |

---

## 🎯 Maven Commands


mvn clean compile              # Build project
mvn test                      # Run all tests
mvn test -Dtest=LoginTest     # Run specific test
mvn clean test -DsuiteXmlFile=testng.xml  # Run TestNG suite


---

## 📁 Repository Structure

orangehrm-selenium-framework/
├── .gitignore
├── pom.xml
├── src/main/java/com/OrangeHRM/UI/
│   ├── basePage/
│   ├── config/
│   └── pageObjects/
├── src/main/resources/
│   └── config.properties
└── src/test/
    ├── java/com/OrangeHRM/UITest/
    │   ├── testBase/
    │   └── testCases/
    └── resources/
        └── testng.xml


---

**🔗 Repository URL**: https://github.com/TeluguAutomation/orangehrm-selenium-framework

**📚 Full Guide**: See `GITHUB_GUIDE.md` for detailed documentation
