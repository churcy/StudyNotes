import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by Churcy on 2016/7/22.
 */
public class ChannelExample {

    @Test
    public void testChannelToChannel() throws IOException {
        /****fromChannel and toChannel****/
        RandomAccessFile fromFile = new RandomAccessFile("E:\\Workspaces\\IdeaProjects\\StudyNotes\\nio\\src\\main\\java\\com.tinytree.nio\\fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("E:\\Workspaces\\IdeaProjects\\StudyNotes\\nio\\src\\main\\java\\com.tinytree.nio\\toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        //transferFrom
        //toChannel.transferFrom(fromChannel,position,count);
        //transferTo
        fromChannel.transferTo(position,count,toChannel);
    }
}
