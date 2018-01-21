package sample.jsp;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by eshaul on 1/21/18.
 */
@Service
public class GcsService {

    private static final String BUCKET_NAME = "ide-summit";
    private static final String BLOB_NAME = "input.txt";

    String getBlobText() {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        Blob blob = storage.get(BlobId.of(BUCKET_NAME, BLOB_NAME));
        if (blob == null) {
            return String.format("error: blob %s not found", BLOB_NAME);
        }

        try {
            return new String(blob.getContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
