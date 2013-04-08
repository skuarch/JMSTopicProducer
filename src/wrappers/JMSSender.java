package wrappers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.naming.NamingException;
import net.jms.JMSTopicConnection;

/**
 *
 * @author skuarch
 */
public class JMSSender extends JMSTopicConnection {

    private MessageProducer messageProducer = null;
    private Message message = null;

    //==========================================================================
    /**
     * create a instance
     */
    public JMSSender() {
        super("jms_topic_connection_factory", "jndi_jms_topic");
    } // end JMSSender

    //==========================================================================
    /**
     * send message to queue.
     */
    public void sendMessage() throws FileNotFoundException, IOException, NamingException, JMSException {

        try {

            setUpPropertiesFile("configuration/jndi.properties");
            createInitialContext();
            createTopicConnectionFactory();
            createTopicConnection();
            createTopic();
            createSession();
            createSubscriber();
            createMessageProducer();

            messageProducer = getMessageProducer();
            message = getTopicSession().createMessage();
            message.setJMSType("Message");
            message.setStringProperty("myPropertie", "myValue");
            message.setStringProperty("hostnameSender", InetAddress.getLocalHost().getHostName());
            messageProducer.send(message);

        } catch (IOException ioe) {
            throw ioe;
        } catch (NamingException ne) {
            throw ne;
        } catch (JMSException jmse) {
            throw jmse;
        } finally {
            closeTopicConnection();
            closeTopicSession();
            closeTopicSubscriber();
            closeMessageProducer();
        }

    } // end sendMessage
} // end class
