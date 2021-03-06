package com.tinytree.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Created by Churcy on 2016/7/21.
 */
public class BufferExample {


    /**
     * 为了理解Buffer的工作原理，需要熟悉它的三个属性： position和limit的含义取决于Buffer处在读模式还是写模式。capacity的含义总是一样的。
     * capacity
     * 作为一个内存块，Buffer有一个固定的大小值，
     * 也叫“capacity”.你只能往里写capacity个byte、long，char等类型。一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。
     * position
     * 当你写数据到Buffer中时，position表示当前的位置。
     * 初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1.
     * 当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。
     * limit
     * 在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
     * 当切换Buffer到读模式时， limit表示你最多能读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。
     * 换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
     *
     * @throws IOException
     */
    @Test
    public void testFileChannel() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("E:\\Workspaces\\IdeaProjects\\StudyNotes\\nio\\src\\main\\java\\com.tinytree.nio\\nio-data.txt", "rw");
        java.nio.channels.FileChannel inChannel = accessFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        //read into buffer
        //从Channel写到Buffer的例子
        //int bytesRead = inChannel.read(buf); //read into buffer.
        //通过put方法写Buffer的例子：
        //buf.put(127);
        int bytesRead = inChannel.read(buf);
        System.out.println("测试FileChannel!");
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            //注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
            //make buffer ready for read
            //flip方法将Buffer从写模式切换到读模式
            buf.flip();
            while (buf.hasRemaining()) {
                // read 1 byte at a time
                //从Buffer读取数据到Channel的例子：
                //read from buffer into channel.
                //int bytesWritten = inChannel.write(buf);
                //使用get()方法从Buffer中读取数据的例子
                byte aByte = buf.get();

                System.out.print((char) buf.get());
            }
            buf.clear();//make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        accessFile.close();
    }
    //rewind()方法
    //BufferExample.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。
    //clear()与compact()方法
    //一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。
    //如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，BufferExample 被清空了。Buffer中的数据并未清除，
    //只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
    //如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。
    //如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
    // compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，
    // 设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。
    //mark()与reset()方法
    //通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position。例如：
    //buffer.mark();
    //call buffer.get() a couple of times, e.g. during parsing.
    // buffer.reset();  //set position back to mark.
    // equals()与compareTo()方法
    // 可以使用equals()和compareTo()方法两个Buffer。
    //equals()
    //当满足下列条件时，表示两个Buffer相等：
    //有相同的类型（byte、char、int等）。
    //Buffer中剩余的byte、char等的个数相等。
    //Buffer中所有剩余的byte、char等都相同。
    //如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。
    //compareTo()方法
    //compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：
    //第一个不相等的元素小于另一个Buffer中对应的元素 。
    // 所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。
}
