def buildAndRun() {
    def resolvedAntHome = tool name: 'apache-ant-1.10.14', type: 'hudson.tasks.Ant$AntInstallation'
    def resolvedJdkHome = tool name: 'jdk-17.0.2', type: 'hudson.model.JDK'

    withEnv([
            "ANT_HOME=${resolvedAntHome}"
            ,"JAVA_HOME=${resolvedJdkHome}"
            ,"PATH=${resolvedAntHome}/bin;${resolvedJdkHome}/bin;${env.PATH}"
    ]) {
        stage('Build') {
            dir("./ant-sample") {
                bat "${env.ANT_HOME}\\bin\\ant.bat -f ${env.WORKSPACE}\\build.xml"
            }
        }
        stage('Run') {
            dir("./ant-sample") {
                bat "${env.JAVA_HOME}\\bin\\java.exe -jar ${env.WORKSPACE}\\build\\jar\\ant-sample.jar"
            }
        }
    }
}

return this

