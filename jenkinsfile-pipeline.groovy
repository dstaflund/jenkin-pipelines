node('Jenkins-Agent') {
    stage('Checkout') {
        checkout([
                $class                           : 'GitSCM',
                branches                         : [[name: 'origin/master']],
                doGenerateSubmoduleConfigurations: false,
                extensions                       : [
                        [$class: 'CheckoutOption', timeout: 15],
                        [$class: 'CloneOption', timeout: 15, shallow: true]
                ],
                submoduleCfg                     : [],
                userRemoteConfigs                : [[url: 'https://github.com/dstaflund/ant-sample.git']]
        ])
    }

    def flow = load pwd() + '.\\jenkinsfile.groovy'
    flow.buildAndRun()
}
