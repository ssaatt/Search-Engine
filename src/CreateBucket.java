
	import java.io.IOException;

	import com.amazonaws.auth.AWSCredentials;
	import com.amazonaws.auth.PropertiesCredentials;
	import com.amazonaws.services.s3.AmazonS3Client;


	public class CreateBucket {

		public static void main(String[] args) throws IOException {
			
		   	 AWSCredentials credentials = new PropertiesCredentials(
					 CreateBucket.class.getResourceAsStream("AwsCredentials.properties"));
		   	 
		   	AmazonS3Client s3 = new AmazonS3Client(credentials);
			
	        String bucketName = "ngnprojectbucket";
			s3.createBucket(bucketName);
            System.out.println("A new S3 bucket named: [ "+ bucketName + " ] has been created successfully.");
		}

	}


