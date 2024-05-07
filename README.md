# REQUIREMENTS

- git
- java 17
- gradle 8.7
- [detect-secrets](https://github.com/Yelp/detect-secrets)
- node >= 20

# SETUP

```console
git clone https://github.com/gipo999/test-spi
cd test-spi
npm install
```

# USAGE

list all gradle tasks

```console
gradle tasks
```

gradle spotless lint check

```console
gradle spotlessCheck
```

gradle spotless format

```console
gradle spotlessApply
```

gradle test

```console
gradle test
```

gradle build

```console
gradle build
```

### test-spi

- junit
- badge su homepage <https://docs.github.com/en/actions/monitoring-and-troubleshooting-workflows/adding-a-workflow-status-badge>
- formatting (google style) <https://peterevans.dev/posts/github-actions-how-to-automate-code-formatting-in-pull-requests/>
- pit <https://4comprehension.com/integrating-pit-mutation-testing-and-github-pages-with-github-actions/>
- sonarlint / sonarqube <https://community.sonarsource.com/t/how-to-block-the-merge-of-pull-requests-when-sonarqube-quality-gate-is-failed-with-github/19516/2>
- dependabot <https://www.linkedin.com/posts/gradle_gradle-dependency-submission-action-for-github-ugcPost-7186736580571619329-WBos?utm_source=share&utm_medium=member_android>
- check-dep-vuln <https://github.com/actions/dependency-review-action>
- deploy su maven-central

## google java format

working command to format all java files in the project
requires google-java-format to be installed and find, linux only
using gradle spotless instead

```console
google-java-format -i $(find . -type f -name "\*.java")
```

# TODOs

spotless
<https://github.com/diffplug/spotless/tree/main/plugin-gradle>

- [ ] add snyk
- [ ] add semgrep
- [ ] add husky hooks
- [ ] add commitlint
- [ ] add semantic-release
- [ ] add lint-staged for google-java-format or spotless?

## using pre-commit framework

<https://pre-commit.com>

added 0 dependency executable with git-lfs in root folder

## adding commitizen

<https://cz-git.qbb.sh/guide/>
<https://commitlint.js.org/>

alternatives:
<https://commitizen.github.io/cz-cli/>
<https://github.com/commitizen/cz-cli>
<https://pre-commit.com/> (python)
