- ./gradlew generateModulesGraphStatistics
- ./gradlew generateModulesGraphvizText -Pmodules.graph.output.gv=all_modules

[//]: # (-Pmodules.graph.output.gv=all_modules2)

- dot -Tpng all_modules -o all_modules.png


- file:///Users/punchman/MyProjects/shmr-25/reports/size/release/report.html
- file:///Users/punchman/MyProjects/shmr-25/reports/size/debug/report.html