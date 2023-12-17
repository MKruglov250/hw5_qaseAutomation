pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    stages {
        stage('Test') {
            steps {
                // Run Maven Verify
                sh "mvn verify"
            }


            post {
                    always {
                        allure([
                                                includeProperties: false,
                                                jdk: '',
                                                properties: [],
                                                reportBuildPolicy: 'ALWAYS',
                                                results: [[path: 'target/allure-results']]
                                            ])
                    }
            }
        }
    }
}
