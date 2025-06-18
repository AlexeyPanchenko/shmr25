- ./gradlew generateModulesGraphStatistics
- ./gradlew generateModulesGraphvizText -Pmodules.graph.output.gv=all_modules

[//]: # (-Pmodules.graph.of.module=:features:feature1)

- dot -Tpng all_modules -o all_modules.png


- file:///Users/punchman/MyProjects/shmr-25/reports/size/release/report.html
- file:///Users/punchman/MyProjects/shmr-25/reports/size/debug/report.html

- ./gradlew assertModuleGraph

- ./gradlew detekt

- ./gradlew :app:analyzeDebugBundle


https://community.gradle.org/github-actions/docs/setup-gradle/#caching-build-state-between-jobs

https://docs.github.com/en/code-security/code-scanning/integrating-with-code-scanning/uploading-a-sarif-file-to-github