import jenkins.model.*

def instance  = Jenkins.getInstance()
def plugins   = [
	 "jaxb"
	,"token-macro"
	,"kubernetes"
	,"scm-api"
	,"workflow-cps"
	,"pipeline-model-extensions"
	,"pipeline-milestone-step"
	,"kubernetes-credentials"
	,"workflow-aggregator"
	,"subversion"
	,"gradle"
	,"ssh-credentials"
	,"workflow-step-api"
	,"pipeline-graph-analysis"
	,"mailer"
	,"display-url-api"
	,"github-branch-source"
	,"workflow-multibranch"
	,"matrix-auth"
	,"branch-api"
	,"credentials"
	,"docker-commons"
	,"slack"
	,"timestamper"
	,"workflow-api"
	,"workflow-support"
	,"build-timeout"
	,"github"
	,"plain-credentials"
	,"matrix-project"
	,"junit"
	,"email-ext"
	,"pipeline-build-step"
	,"pipeline-model-api"
	,"cloudbees-folder"
	,"kubernetes-client-api"
	,"trilead-api"
	,"pipeline-model-declarative-agent"
	,"workflow-basic-steps"
	,"resource-disposer"
	,"pipeline-stage-step"
	,"pam-auth"
	,"git"
	,"lockable-resources"
	,"git-client"
	,"credentials-binding"
	,"pipeline-input-step"
	,"pipeline-model-definition"
	,"antisamy-markup-formatter"
	,"script-security"
	,"ace-editor"
	,"ant"
	,"pipeline-github-lib"
	,"mapdb-api"
	,"ldap"
	,"ws-cleanup"
	,"github-api"
	,"pipeline-stage-view"
	,"jdk-tool"
	,"workflow-scm-step"
	,"command-launcher"
	,"jackson2-api"
	,"ssh-slaves"
	,"durable-task"
	,"structs"
	,"apache-httpcomponents-client-4-api"
	,"docker-workflow"
	,"workflow-cps-global-lib"
	,"pipeline-stage-tags-metadata"
	,"bouncycastle-api"
	,"authentication-tokens"
	,"momentjs"
	,"variant"
	,"workflow-durable-task-step"
	,"jquery-detached"
	,"workflow-job"
	,"handlebars"
	,"git-server"
	,"jsch"
	,"pipeline-rest-api"
]

pm = instance.getPluginManager()
uc = instance.getUpdateCenter()

uc.updateAllSites()

def enablePlugin(pluginName) {
  if (! pm.getPlugin(pluginName)) {
    deployment = uc.getPlugin(pluginName).deploy(true)
    deployment.get()
  }

  def plugin = pm.getPlugin(pluginName)
  if (! plugin.isEnabled()) {
    plugin.enable()
  }

  plugin.getDependencies().each {
    enablePlugin(it.shortName)
  }
}

plugins.each {
  def plugin = pm.getPlugin(it)
  enablePlugin(it)
}

