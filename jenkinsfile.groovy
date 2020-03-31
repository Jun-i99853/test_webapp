node {
	def useTest = true
	def useBuild = true
	def useDeploy = true
	
    stage('FlowCheck') {
        try {
	        println "  TEST FLOW = $USE_TEST"
	        useTest = "$USE_TEST" == "true"
	    }
	    catch (MissingPropertyException e) {
	        println "  TEST FLOW = true"
	    }
	
	    try {
	        println "  BUILD FLOW = $USE_BUILD"
	        useBuild = "$USE_BUILD" == "true"
	    }
	    catch (MissingPropertyException e) {
	        println "  BUILD FLOW = true"
	    }
	
	    try {
	        println "  DEPLOY FLOW = $USE_DEPLOY"
	        useBuild = "$USE_DEPLOY" == "true"
	    }
	    catch (MissingPropertyException e) {
	        println "  BUILD DEPLOY = true"
	    }
	
	    try {
	        println "  SWITCH FLOW = $USE_SWITCH"
	        useBuild = "$USE_SWITCH" == "true"
	    }
	    catch (MissingPropertyException e) {
	        println "  SWITCH FLOW = true"
	    }
    }
    stage('ParameterCheck') {
        println "  JAVA_VERSION = $JAVA_VERSION"
        println "  GIT_URL = $GIT_URL"
    }
    stage('Checkout') {
    	if (useTest || useBuild) {
    		println "Git Checkout Started"
    		checkout(
                [
                        $class                           : 'GitSCM',
                        branches                         : [[name: '${BRANCH_SELECTOR}']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[url: '${GIT_URL}']]
                ]
        	)
        	println "Git CheckOut End"
    	} else {
	        println "Git CheckOut Skip"
	    }
    }
    stage('Build') {
    }
    stage('Test') {
    	if (useTest) {
	        println "Test Started"
	        println "Test End"
	    } else {
	        println "Test Skip"
	    }
    }
    stage('Deploy') {
    	if(useDeploy) {
	        println "Deploy Started"
	        println "Deploy End"
	    } else {
	        println "Deploy Skip"
	    }
    }
}