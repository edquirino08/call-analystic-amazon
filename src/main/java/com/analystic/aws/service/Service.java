package com.analystic.aws.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.analystic.aws.model.CallAnalysticModel;
import com.analystic.aws.model.Transcript;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.CallAnalyticsJobSettings;
import software.amazon.awssdk.services.transcribe.model.ChannelDefinition;
import software.amazon.awssdk.services.transcribe.model.Media;
import software.amazon.awssdk.services.transcribe.model.ParticipantRole;
import software.amazon.awssdk.services.transcribe.model.StartCallAnalyticsJobRequest;
import software.amazon.awssdk.services.transcribe.model.StartCallAnalyticsJobResponse;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private final static AwsBasicCredentials aswCredentials = AwsBasicCredentials.create("",
			"");

	public void transcribe() {

		Region REGION = Region.US_EAST_1;
		TranscribeClient client;

		client = TranscribeClient.builder().credentialsProvider(StaticCredentialsProvider.create(aswCredentials))
				.region(REGION).build();

		String s3AudioPath = "s3://call-analytic-quirino/somalia.mp3";

		String transcriptionJobName = "9";
		Media myMedia = Media.builder().mediaFileUri(s3AudioPath).build();

		String outputS3BucketName = "s3://call-analytic-quirino";

		CallAnalyticsJobSettings settings = CallAnalyticsJobSettings.builder().build();

		ChannelDefinition channel = ChannelDefinition.builder().channelId(0).participantRole(ParticipantRole.AGENT)
				.build();

		ChannelDefinition channelDefinition = ChannelDefinition.builder().channelId(1)
				.participantRole(ParticipantRole.CUSTOMER).build();

		List<ChannelDefinition> list = new ArrayList<>();

		list.add(channel);
		list.add(channelDefinition);

		StartCallAnalyticsJobRequest request = StartCallAnalyticsJobRequest.builder()
				.callAnalyticsJobName(transcriptionJobName).settings(settings).channelDefinitions(list).media(myMedia)
				.outputLocation(outputS3BucketName).build();

		StartCallAnalyticsJobResponse response = client.startCallAnalyticsJob(request);

		System.out.println("Created the transcription job");
		System.out.println(response.callAnalyticsJob());
	}

	public void print() {
		String str = "{\"JobStatus\":\"COMPLETED\",\"LanguageCode\":\"pt-BR\",\"Transcript\":[{\"LoudnessScores\":[38.79,83.31,88.68,40.36],\"Content\":\"mãe tá me ouvindo?\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":0.9853,\"Content\":\"mãe\",\"BeginOffsetMillis\":550,\"EndOffsetMillis\":1290},{\"Type\":\"pronunciation\",\"Confidence\":0.9762,\"Content\":\"tá\",\"BeginOffsetMillis\":2760,\"EndOffsetMillis\":3000},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"me\",\"BeginOffsetMillis\":3000,\"EndOffsetMillis\":3080},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"ouvindo\",\"BeginOffsetMillis\":3080,\"EndOffsetMillis\":3630},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\"?\"}],\"Id\":\"087ca19b-2fc7-424a-b1ae-8eea845fc6fc\",\"BeginOffsetMillis\":550,\"EndOffsetMillis\":3630,\"Sentiment\":\"NEUTRAL\",\"ParticipantRole\":\"CUSTOMER\"},{\"LoudnessScores\":[74.87,67.44,77.74,47.03,78.08,79.35,78.37,74.35,75.86,78.76,78.1],\"Content\":\"Alô oi, Tô tá, pode vontade!\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Alô\",\"BeginOffsetMillis\":1220,\"EndOffsetMillis\":1780},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"oi\",\"BeginOffsetMillis\":1790,\"EndOffsetMillis\":2540},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":0.6178,\"Content\":\"Tô\",\"BeginOffsetMillis\":3980,\"EndOffsetMillis\":4780},{\"Type\":\"pronunciation\",\"Confidence\":0.9726,\"Content\":\"tá\",\"BeginOffsetMillis\":8280,\"EndOffsetMillis\":9080},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"pode\",\"BeginOffsetMillis\":10400,\"EndOffsetMillis\":10870},{\"Type\":\"pronunciation\",\"Confidence\":0.9949,\"Content\":\"vontade\",\"BeginOffsetMillis\":10880,\"EndOffsetMillis\":11810},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\"!\"}],\"Id\":\"3c2c2036-77c1-4edb-a4f2-71febaa3dd0f\",\"BeginOffsetMillis\":1220,\"EndOffsetMillis\":11810,\"Sentiment\":\"POSITIVE\",\"ParticipantRole\":\"AGENT\"},{\"LoudnessScores\":[39.61,41.22,42.68,84.06,82.43,81.0,80.08,65.65,86.96,86.57,83.3,41.22,42.75],\"Content\":\"Tá, eu Tô fazendo só um teste aqui, tá de ligação! Aí fala assim Teste Eduardo, Tá, beleza, então é só isso.\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":0.9175,\"Content\":\"Tá\",\"BeginOffsetMillis\":5350,\"EndOffsetMillis\":5740},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"eu\",\"BeginOffsetMillis\":5740,\"EndOffsetMillis\":5850},{\"Type\":\"pronunciation\",\"Confidence\":0.9034,\"Content\":\"Tô\",\"BeginOffsetMillis\":5850,\"EndOffsetMillis\":5980},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"fazendo\",\"BeginOffsetMillis\":5980,\"EndOffsetMillis\":6350},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"só\",\"BeginOffsetMillis\":6350,\"EndOffsetMillis\":6540},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"um\",\"BeginOffsetMillis\":6540,\"EndOffsetMillis\":6640},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"teste\",\"BeginOffsetMillis\":6640,\"EndOffsetMillis\":7050},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"aqui\",\"BeginOffsetMillis\":7050,\"EndOffsetMillis\":7350},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":0.9439,\"Content\":\"tá\",\"BeginOffsetMillis\":7350,\"EndOffsetMillis\":7780},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"de\",\"BeginOffsetMillis\":8560,\"EndOffsetMillis\":8840},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"ligação\",\"BeginOffsetMillis\":8840,\"EndOffsetMillis\":9570},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\"!\"},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Aí\",\"BeginOffsetMillis\":9580,\"EndOffsetMillis\":10370},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"fala\",\"BeginOffsetMillis\":10370,\"EndOffsetMillis\":10650},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"assim\",\"BeginOffsetMillis\":10650,\"EndOffsetMillis\":10940},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Teste\",\"BeginOffsetMillis\":10940,\"EndOffsetMillis\":11570},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Eduardo\",\"BeginOffsetMillis\":11570,\"EndOffsetMillis\":12280},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":0.9942,\"Content\":\"Tá\",\"BeginOffsetMillis\":15450,\"EndOffsetMillis\":16170},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"beleza\",\"BeginOffsetMillis\":16180,\"EndOffsetMillis\":16630},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\",\"},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"então\",\"BeginOffsetMillis\":16630,\"EndOffsetMillis\":16870},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"é\",\"BeginOffsetMillis\":16870,\"EndOffsetMillis\":16930},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"só\",\"BeginOffsetMillis\":16930,\"EndOffsetMillis\":17130},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"isso\",\"BeginOffsetMillis\":17130,\"EndOffsetMillis\":17310},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\".\"}],\"Id\":\"b3e2dcd8-d34e-42f9-ade8-37621c10e014\",\"BeginOffsetMillis\":5350,\"EndOffsetMillis\":17310,\"Sentiment\":\"NEUTRAL\",\"ParticipantRole\":\"CUSTOMER\"},{\"LoudnessScores\":[51.66,49.72,72.22,74.08,75.62,73.71,57.44],\"Content\":\"Teste Eduardo nada.\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Teste\",\"BeginOffsetMillis\":13000,\"EndOffsetMillis\":14130},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"Eduardo\",\"BeginOffsetMillis\":14140,\"EndOffsetMillis\":15300},{\"Type\":\"pronunciation\",\"Confidence\":1.0,\"Content\":\"nada\",\"BeginOffsetMillis\":18490,\"EndOffsetMillis\":19770},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\".\"}],\"Id\":\"12cb8fd1-c3e4-4c75-b5eb-d39bb9bb1da1\",\"BeginOffsetMillis\":13000,\"EndOffsetMillis\":19770,\"Sentiment\":\"NEUTRAL\",\"ParticipantRole\":\"AGENT\"},{\"LoudnessScores\":[42.75,83.24,83.0,73.33],\"Content\":\"Obrigado também tá.\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":0.9936,\"Content\":\"Obrigado\",\"BeginOffsetMillis\":17310,\"EndOffsetMillis\":17660},{\"Type\":\"pronunciation\",\"Confidence\":0.9946,\"Content\":\"também\",\"BeginOffsetMillis\":17660,\"EndOffsetMillis\":18110},{\"Type\":\"pronunciation\",\"Confidence\":0.8011,\"Content\":\"tá\",\"BeginOffsetMillis\":19800,\"EndOffsetMillis\":20010},{\"Type\":\"punctuation\",\"Confidence\":0.0,\"Content\":\".\"}],\"Id\":\"2dd5c1aa-17d8-4ae6-a70e-f1dbd9fc4fd5\",\"BeginOffsetMillis\":17310,\"EndOffsetMillis\":20010,\"Sentiment\":\"POSITIVE\",\"ParticipantRole\":\"CUSTOMER\"},{\"LoudnessScores\":[57.44,55.13],\"Content\":\"Eu\",\"Items\":[{\"Type\":\"pronunciation\",\"Confidence\":0.9433,\"Content\":\"Eu\",\"BeginOffsetMillis\":19780,\"EndOffsetMillis\":20320}],\"Id\":\"9a7e995a-5ee2-43c8-a126-65692dde9ce3\",\"BeginOffsetMillis\":19780,\"EndOffsetMillis\":20320,\"Sentiment\":\"NEUTRAL\",\"ParticipantRole\":\"AGENT\"}],\"AccountId\":\"916841971192\",\"Categories\":{\"MatchedDetails\":{},\"MatchedCategories\":[]},\"Channel\":\"VOICE\",\"JobName\":\"5\",\"Participants\":[{\"ParticipantRole\":\"AGENT\"},{\"ParticipantRole\":\"CUSTOMER\"}],\"ConversationCharacteristics\":{\"NonTalkTime\":{\"Instances\":[],\"TotalTimeMillis\":0},\"Interruptions\":{\"TotalCount\":3,\"TotalTimeMillis\":15880,\"InterruptionsByInterrupter\":{\"AGENT\":[{\"BeginOffsetMillis\":1220,\"DurationMillis\":2410,\"EndOffsetMillis\":3630},{\"BeginOffsetMillis\":13000,\"DurationMillis\":7010,\"EndOffsetMillis\":20010}],\"CUSTOMER\":[{\"BeginOffsetMillis\":5350,\"DurationMillis\":6460,\"EndOffsetMillis\":11810}]}},\"TotalConversationDurationMillis\":20320,\"Sentiment\":{\"OverallSentiment\":{\"AGENT\":1.7,\"CUSTOMER\":1.7},\"SentimentByPeriod\":{\"QUARTER\":{\"AGENT\":[{\"Score\":5.0,\"BeginOffsetMillis\":0,\"EndOffsetMillis\":5080},{\"Score\":0.0,\"BeginOffsetMillis\":5080,\"EndOffsetMillis\":10160},{\"Score\":0.0,\"BeginOffsetMillis\":10160,\"EndOffsetMillis\":15240},{\"Score\":0.0,\"BeginOffsetMillis\":15240,\"EndOffsetMillis\":20320}],\"CUSTOMER\":[{\"Score\":0.0,\"BeginOffsetMillis\":0,\"EndOffsetMillis\":5002},{\"Score\":0.0,\"BeginOffsetMillis\":5002,\"EndOffsetMillis\":10005},{\"Score\":0.0,\"BeginOffsetMillis\":10005,\"EndOffsetMillis\":15007},{\"Score\":5.0,\"BeginOffsetMillis\":15007,\"EndOffsetMillis\":20010}]}}},\"TalkSpeed\":{\"DetailsByParticipant\":{\"AGENT\":{\"AverageWordsPerMinute\":34},\"CUSTOMER\":{\"AverageWordsPerMinute\":98}}},\"TalkTime\":{\"DetailsByParticipant\":{\"AGENT\":{\"TotalTimeMillis\":17900},\"CUSTOMER\":{\"TotalTimeMillis\":17730}},\"TotalTimeMillis\":35630}},\"ContentMetadata\":{\"Output\":\"Raw\"}}";

		JSONObject jsonObject = new JSONObject(str);

		JSONArray results = jsonObject.getJSONArray("Transcript");

		CallAnalysticModel transcript = new CallAnalysticModel();

		for (int i = 0; i < results.length(); i++) {

			Transcript transcriptX = new Transcript(results.getJSONObject(i).get("Content").toString(),
					results.getJSONObject(i).get("ParticipantRole").toString(),
					results.getJSONObject(i).get("Sentiment").toString());

			transcript.getMessages().add(transcriptX);

		}

		for (int i = 0; i < transcript.getMessages().size(); i++) {

			System.out.println("Menssagem :" + transcript.getMessages().get(i).getContent());
			System.out.println("Agente :" + transcript.getMessages().get(i).getParticipantRole());
			System.out.println("Sentiment :" + transcript.getMessages().get(i).getSentiment());
			System.out.println("\n");
			System.out.println("--------------------------------------------------");
		}

	}

}
