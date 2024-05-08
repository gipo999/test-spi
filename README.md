# REQUIREMENTS

TODO: are java and gradle required with gradle wrapper?

- python >= 3.9
- git
- java 17
- gradle cli 8.7
- node >= 20

# SETUP

```console
git clone https://github.com/gipo999/test-spi
cd test-spi
npm install
```

## DEVELOPMENT

clone the repo, run `npm install`, make changes and run `npm run commit`
This will trigger the pre-commit pipeline and enforce the commit message format

# NOTES

spotless
<https://github.com/diffplug/spotless/tree/main/plugin-gradle>

## using pre-commit framework

<https://pre-commit.com>

added 0 dependency executable with git-lfs in root folder
will take care of providing the detect-secrets executable

## adding commitizen

<https://cz-git.qbb.sh/guide/>
<https://commitlint.js.org/>

alternatives:
<https://commitizen.github.io/cz-cli/>
<https://github.com/commitizen/cz-cli>
<https://pre-commit.com/> (python)
