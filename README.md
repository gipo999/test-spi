# REQUIREMENTS

- java 17
- gradle 8.7
- [detect-secrets](https://github.com/Yelp/detect-secrets)
- node >= 20

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

## adding commitizen

<https://commitizen.github.io/cz-cli/>
<https://github.com/commitizen/cz-cli>
<https://commitlint.js.org/guides/use-prompt.html> using the commitizen alternative
<https://cz-git.qbb.sh/guide/>
