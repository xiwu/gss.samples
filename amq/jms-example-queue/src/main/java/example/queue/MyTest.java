package example.queue;
import java.nio.ByteBuffer;

import sun.misc.SharedSecrets;
public class MyTest {
	/**
	 * Write amount of direct memory used to standard output
	 * using SharedSecrets, JavaNetAccess, the direct Buffer Pool,
	 * and methods getMemoryUsed() and getTotalCapacity().
	 */
	public static void writeUsedDirectMemoryToStdOut()
	{
		final ByteBuffer bytes = ByteBuffer.allocateDirect(1_000_000);
	   final double sharedSecretsMemoryUsed =
	      MemoryUnit.BYTES.toMegaBytes(
	         SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed());
	   System.out.println(
	      "sun.misc.SharedSecrets.getJavaNioAccess().getDirectBufferPool().getMemoryUsed(): "
	         + sharedSecretsMemoryUsed + " MB");
	   final double sharedSecretsTotalCapacity =
	      MemoryUnit.BYTES.toMegaBytes(SharedSecrets.getJavaNioAccess().getDirectBufferPool().getTotalCapacity());
	   System.out.println("sun.misc.SharedSecrets.getJavaNioAccess().getDirectBufferPool().getTotalCapacity(): "
	      + sharedSecretsTotalCapacity + " MB");
	}	
	
	public static void main(String[] args) {
		MyTest.writeUsedDirectMemoryToStdOut();
	}
}