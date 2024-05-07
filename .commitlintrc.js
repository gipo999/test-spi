// .commitlintrc.js
// const fs = require("node:fs");
// const path = require("node:path");

// can propose scope for commit using folder names
// const apps = fs.readdirSync(path.resolve(__dirname, "apps"));

const { execSync } = require("child_process");

// @tip: git branch name = feature/issue_33   =>    auto get defaultIssues = #33
const issue = execSync("git rev-parse --abbrev-ref HEAD")
  .toString()
  .trim()
  .split("_")[1];

// .commitlintrc.js
/** @type {import('cz-git').UserConfig} */
module.exports = {
  extends: ["@commitlint/config-conventional"],
  rules: {
    // @see: https://commitlint.js.org/#/reference-rules
    "scope-enum": [
      2,
      "always",
      [
        "global", // used to denote global changes
        // ...apps,
      ],
    ],
  },
  prompt: {
    useEmoji: true,
    customIssuePrefixAlign: !issue ? "top" : "bottom",
    defaultIssues: !issue ? "" : `#${issue}`,
  },
};
