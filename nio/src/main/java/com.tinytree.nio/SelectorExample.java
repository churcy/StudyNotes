import org.junit.Test;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Churcy on 2016/7/22.
 * 仅用单个线程来处理多个Channels的好处是，只需要更少的线程来处理通道。
 * 事实上，可以只用一个线程处理所有的通道。对于操作系统来说，线程之间上下文切换的开销很大，而且每个线程都要占用系统的一些资源（如内存）。
 * 因此，使用的线程越少越好。
 */
public class SelectorExample {
    @Test
    public void testSelector() throws IOException {
        //通过调用Selector.open()方法创建一个Selector
        Selector selector = Selector.open();
        //向Selector注册通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        //SelectionKey.OP_CONNECT
        //SelectionKey.OP_ACCEPT
        //SelectionKey.OP_READ
        //SelectionKey.OP_WRITE
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        while (true) {
            // select()阻塞，等待有事件发生唤醒
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;
            //Set selectedKeys = selector.selectedKeys();
            //Iterator keyIterator = selectedKeys.iterator();
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey selectionKey = selectedKeys.next();
                if (selectionKey.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (selectionKey.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (selectionKey.isReadable()) {
                    // a channel is ready for reading
                } else if (selectionKey.isWritable()) {
                    // a channel is ready for writing
                }
                selectedKeys.remove();
            }
        }
    }
}
