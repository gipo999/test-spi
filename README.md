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

## adding commitizen

the problem with git hooks is windows compatibility.
hooks are meant for interpreted shells.
some commands are not available on windows, like `sed` or `awk` or `xargs` to perform git operations.

The main problems are on the `prepare-commit-msg` hook, where the commit message is prepared for the user,
and the `pre-commit` hook, where detect-secrets is run.

in the pre-commit, husky doesn't work on windows for the detect-secrets command as it requires populating the detect-secrets-hooks with the files being committed.
in the prepare-commit-msg, we can not intercept the commit to force the user to use commitizen cli.

we can ask to use npm run commit is used, which triggers the pre-commit pipeline and add commitlint to block the commit if the message is not formatted correctly.

in this case, we move the pre-commit outside since it won't trigger when runnig cz.
this will leave out pre-commit.

found this undocumented cmd
<https://github.com/commitizen/cz-cli/issues/132>

with pre-commit: <https://github.com/commitizen/cz-cli/issues/801>

<https://cz-git.qbb.sh/guide/> (node)
<https://commitlint.js.org/> (node)

alternatives:
<https://commitizen.github.io/cz-cli/> (node)
<https://github.com/commitizen/cz-cli> (node)
<https://github.com/commitizen-tools/commitizen> (python)
<https://pre-commit.com/> (python)
