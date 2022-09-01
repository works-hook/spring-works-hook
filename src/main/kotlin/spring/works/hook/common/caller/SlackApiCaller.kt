package spring.works.hook.common.caller

interface SlackApiCaller {

    fun sendSlackBot(uri: String, data: Any)
}
