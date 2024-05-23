const { spawnSync } = require("child_process");

module.exports = {
  execCmd: (cmd) => {
    console.log(`Running: ${cmd}`);

    const [exec, ...args] = cmd.split(" ");

    const currentCmd = spawnSync(exec, args, {
      stdio: "inherit",
    });
  },
};
