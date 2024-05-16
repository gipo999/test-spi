module.exports = {
  // choose the branch to release from
  release: {
    branches: [
      "main",
      "dev",
      "next",
      "nightly",
      "stable",
      "beta",
      "alpha",
      "+([0-9])?(.{+([0-9]),x}).x", // e.g., 1.x, 1.2.x, 1.2.3.x
    ],
  },
  // plugins
  plugins: [
    // commit analyzer, to determine the type of release
    [
      "@semantic-release/commit-analyzer",
      {
        preset: "angular",
        parserOpts: {
          noteKeywords: ["BREAKING CHANGE", "BREAKING CHANGES", "BREAKING"],
        },
      },
    ],
    // release notes generator, which will generate the release notes
    // based on the commits since the last release
    [
      "@semantic-release/release-notes-generator",
      {
        preset: "angular",
        parserOpts: {
          noteKeywords: ["BREAKING CHANGE", "BREAKING CHANGES", "BREAKING"],
        },
        writerOpts: {
          commitsSort: ["subject", "scope"],
        },
      },
    ],
    // exec plugin, to run the publish script and custom commands
    [
      "@semantic-release/exec",
      {
        verifyConditionsCmd: "./verify.sh",
        publishCmd:
          "./publish.sh ${nextRelease.version} ${branch.name} ${commits.length} ${Date.now()}",
      },
    ],
    // github plugin, to create a release on github
    // and add assets to the release
    [
      "@semantic-release/github",
      {
        assets: [
          {
            path: "dist/asset.min.css",
            label: "CSS distribution",
          },
          {
            path: "dist/asset.min.js",
            label: "JS distribution",
          },
        ],
      },
    ],

    // WARNING: semantic-release discourages commits during a release
    // to prevend headaches of conflicts and other issues

    // produce a changelog
    [
      "@semantic-release/changelog",
      {
        changelogFile: "docs/CHANGELOG.md",
      },
    ],
    // commit the changes to the repository
    // possible changelog added, package.json version bump, build.gradle version bump
    [
      "@semantic-release/git",
      {
        assets: ["CHANGELOG.md", "package.json", "build.gradle"],
      },
    ],
  ],
};
