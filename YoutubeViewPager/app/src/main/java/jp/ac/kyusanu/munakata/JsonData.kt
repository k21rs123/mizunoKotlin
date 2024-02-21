package jp.ac.kyusanu.munakata

import kotlinx.serialization.Serializable

@Serializable
data class JsonData(
    val id: Int,
    val videoId: String,
    val question: String,
    val showQuestionnaireTime: Long,//値はnull ?  0なら終了時　０以上なら後ろから、０以下なら頭から
    val choices: List<String>,
    val answerFormatFlag: Int,
    val startDateTime: String,//DateTime
    val endDateTime: String,//DateTime
    val startDateTimeTimeStamp:Long,
    val endDateTimeTimeStamp:Long
)

@Serializable
data class JsonEncode(
    val id: Int,
    val choice: String,
    val answer: String
)