const { exec: execFile } = require("child_process");

const isWindows = process.platform === "win32";

const args = process.argv.slice(2);

const Cmds = {
  check: isWindows ? "gradlew check" : "./gradlew check",
  fix: isWindows ? "gradlew spotlessApply" : "./gradlew spotlessApply",
  build: isWindows ? "gradlew build" : "./gradlew build",
};

const execCmd = (cmd) => {
  execFile(cmd, function (error, stdout, stderr) {
    if (error) {
      console.error(error);
      process.exit(1);
    }
    console.log(stdout);
    console.error(stderr);
  });
};

if (args.includes("--format")) {
  execCmd(Cmds.fix);
}
if (args.includes("--lint")) {
  execCmd(Cmds.check);
}
if (args.includes("--build")) {
  execCmd(Cmds.build);
}
if (args.length === 0) {
  execCmd(`${Cmds.check} && ${Cmds.build}`);
}
