const { execSync } = require("child_process");

const isWindows = process.platform === "win32";

const args = process.argv.slice(2);

const cmds = {
  check: isWindows ? "gradlew check" : "./gradlew check",
  fix: isWindows
    ? "gradlew spotlessApply && gradlew rewriteRun"
    : "./gradlew spotlessApply & ./gradlew rewriteRun",
  build: isWindows ? "gradlew build" : "./gradlew build",
};

const execCmd = (cmd) => {
  execSync(cmd, function (error, stdout, stderr) {
    if (error) {
      console.error(error);
      process.exit(1);
    }
    console.log(stdout);
    console.error(stderr);
  });
};

if (args.includes("--format")) {
  execCmd(cmds.fix);
}
if (args.includes("--lint")) {
  execCmd(cmds.check);
}
if (args.includes("--build")) {
  execCmd(cmds.build);
}
if (args.length === 0) {
  execCmd(`${cmds.build}`);
}
