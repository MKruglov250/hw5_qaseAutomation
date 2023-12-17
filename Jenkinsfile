pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

    parameters {
            gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'webapi', type: 'GitParameterDefinition'
    }

    stages {
        stage('Test') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/MKruglov250/hw5_qaseAutomation.git'

                // Run Maven Verify
                mvn verify
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
