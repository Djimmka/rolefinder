import java.math.BigInteger;
import java.util.logging.Logger;

public class Post_task_4_3_7 {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class StolenPackageException extends RuntimeException {
    }

    ;

    public static class IllegalPackageException extends RuntimeException {
    }

    ;

    public static class UntrustworthyMailWorker implements MailService {

        private MailService[] guests;
        private final RealMailService realMailService = new RealMailService();

        public UntrustworthyMailWorker(MailService[] guests) {
            this.guests = guests;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable proc = mail;
            for (MailService guest : guests) {
                proc = guest.processMail(proc);
            }
            return realMailService.processMail(proc);
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }
    }

    public static class Spy implements MailService {
        private Logger Log;

        public Spy(Logger Log) {
            this.Log = Log;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if ((mail instanceof MailMessage) && ((((MailMessage) mail).getFrom().contains(AUSTIN_POWERS)) || (((MailMessage) mail).getTo().contains(AUSTIN_POWERS)))) {
                Log.warning("Detected target mail correspondence: from " + ((MailMessage) mail).getFrom() + " to " + ((MailMessage) mail).getTo() + " \"" + ((MailMessage) mail).getMessage() + "\"");
            } else {
                if (mail instanceof MailMessage) {
                    Log.info("Usual correspondence: from " + ((MailMessage) mail).getFrom() + " to " + ((MailMessage) mail).getTo());
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int price;
        private int total_value;

        public Thief(int price) {
            this.price = price;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package pack = ((MailPackage) mail).getContent();
                if ((mail instanceof MailPackage) && ((pack.getPrice() >= price))) {
                    total_value += pack.getPrice();
                    Package stolen = new Package("stones instead of " + pack.getContent(), 0);
                    mail = new MailPackage(mail.getFrom(), mail.getTo(), stolen);
                }
            }
            return mail;
        }

        public int getStolenValue() {
            return total_value;
        }
    }

    public static class Inspector implements MailService {


        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package pack = ((MailPackage) mail).getContent();
                if (pack.getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
                if ((pack.getContent().contains(WEAPONS)) || (pack.getContent().contains(BANNED_SUBSTANCE))) {
                    throw new IllegalPackageException();
                }
            }
            return mail;
        }
    }




    public static void main(String[] args) {

    }
}
