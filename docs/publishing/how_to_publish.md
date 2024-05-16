<https://docs.github.com/en/actions/publishing-packages/publishing-java-packages-with-gradle>

- The groupId and artifactId fields in the MavenPublication section of the build.gradle file create a unique identifier for your package that registries use to link your package to a registry.

- Each repository must have a name, a deployment URL, and credentials for authentication.

- Each time you create a new release, you can trigger a workflow to publish your package.

## maven

```groovy
plugins {
  ...
  id 'maven-publish'
}

publishing {
  ...

  repositories {
    maven {
      name = "OSSRH"
      url = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
      credentials {
        username = System.getenv("MAVEN_USERNAME")
        password = System.getenv("MAVEN_PASSWORD")
      }
    }
  }
}
```

## github packages

```groovy
plugins {
  ...
  id 'maven-publish'
}

publishing {
  ...

  repositories {
    maven {
      name = "GitHubPackages"
      url = "https://maven.pkg.github.com/octocat/hello-world"
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
}
```
