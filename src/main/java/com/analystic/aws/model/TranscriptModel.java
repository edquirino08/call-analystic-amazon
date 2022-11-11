package com.analystic.aws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "callanalystic")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bucket_location")
	private String bucektLocation;

	@Column(name = "job_name")
	private String jobName;

	@Column(name = "transcript")
	private String transcript;

}
