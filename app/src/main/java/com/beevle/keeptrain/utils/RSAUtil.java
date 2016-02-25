package com.beevle.keeptrain.utils;//package com.frame.common;
//import javax.crypto.Cipher;
//import java.security.*;
//import java.security.spec.RSAPublicKeySpec;
//import java.security.spec.RSAPrivateKeySpec;
//import java.security.spec.InvalidKeySpecException;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.io.*;
//import java.math.BigInteger;
//
///**
// * RSA �����ࡣ�ṩ���ܣ����ܣ�������Կ�Եȷ�����
// * ��Ҫ��http://www.bouncycastle.org����bcprov-jdk14-123.jar��
// * RSA����ԭ�����  
// * RSA�İ�ȫ�������ڴ����ķֽ⣬��Կ��˽Կ��������������������100��ʮ����λ���ĺ�����  
// * �ݲ²⣬��һ����Կ�������ƶϳ����ĵ��Ѷȵ�ͬ�ڷֽ������������Ļ�  
// * ===================================================================  
// * �����㷨�İ�ȫ��δ�õ����۵�֤����  
// * ===================================================================  
// * ��Կ�Ĳ�����  
// * 1.ѡ������������ p,q ,���� n=p*q;  
// * 2.���ѡ�������Կ e ,Ҫ�� e �� (p-1)*(q-1)����  
// * 3.���� Euclid �㷨���������Կ d , ʹ������ e*d = 1(mod(p-1)*(q-1)) (���� n,d ҲҪ����)  
// * 4:���˵ó���ԿΪ (n,e) ˽ԿΪ (n,d)  
// * ===================================================================  
// * �ӽ��ܷ�����  
// * 1.���Ƚ�Ҫ���ܵ���Ϣ m(�����Ʊ�ʾ) �ֳɵȳ������ݿ� m1,m2,...,mi �鳤 s(�����ܴ�) ,���� 2^s<n  
// * 2:��Ӧ�������ǣ� ci = mi^e(mod n)  
// * 3:����ʱ�����¼��㣺 mi = ci^d(mod n)  
// * ===================================================================  
// * RSA�ٶ�  
// * ���ڽ��еĶ��Ǵ������㣬ʹ��RSA�������Ҳ��DES����100�������� ���������Ӳ��ʵ�֡�  
// * �ٶ�һֱ��RSA��ȱ�ݡ�һ����˵ֻ������������ ���ܡ� 
// *�ļ�����RSAUtil.java<br>
// *@author ����ΰ<br>
// *�汾:<br>
// *������<br>
// *����ʱ��:2008-9-23 ����09:58:16<br>
// *�ļ�������<br>
// *�޸��ߣ�<br>
// *�޸����ڣ�<br>
// *�޸�������<br>
// */
//public class RSAUtil {
//
//	//��Կ��
//	private KeyPair keyPair = null;
//	
//	/**
//	 * ��ʼ����Կ��
//	 */
//	public RSAUtil(){
//		try {
//			this.keyPair = this.generateKeyPair();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	* ������Կ��
//	* @return KeyPair
//	* @throws Exception
//	*/
//	private KeyPair generateKeyPair() throws Exception {
//		try {
//			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",new org.bouncycastle.jce.provider.BouncyCastleProvider());
//			//���ֵ��ϵ������ܵĴ�С�����Ը��ģ����ǲ�Ҫ̫�󣬷���Ч�ʻ��
//			final int KEY_SIZE = 1024;
//			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
//			KeyPair keyPair = keyPairGen.genKeyPair();
//			return keyPair;
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	
//	}
//
//	/**
//	* ���ɹ�Կ
//	* @param modulus
//	* @param publicExponent
//	* @return RSAPublicKey
//	* @throws Exception
//	*/
//	private RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
//	
//		KeyFactory keyFac = null;
//		try {
//			keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
//		} catch (NoSuchAlgorithmException ex) {
//		throw new Exception(ex.getMessage());
//		}
//		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
//		try {
//			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
//		} catch (InvalidKeySpecException ex) {
//			throw new Exception(ex.getMessage());
//		}
//	
//	}
//
//	/**
//	* ����˽Կ
//	* @param modulus
//	* @param privateExponent
//	* @return RSAPrivateKey
//	* @throws Exception
//	*/
//	private RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
//		KeyFactory keyFac = null;
//		try {
//			keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
//		} catch (NoSuchAlgorithmException ex) {
//			throw new Exception(ex.getMessage());
//		}
//		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
//		try {
//			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
//		} catch (InvalidKeySpecException ex) {
//			throw new Exception(ex.getMessage());
//		}
//	}
//
//	/**
//	* ����
//	* @param key ���ܵ���Կ
//	* @param data �����ܵ���������
//	* @return ���ܺ������
//	* @throws Exception
//	*/
//	public byte[] encrypt(Key key, byte[] data) throws Exception {
//		try {
//			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
//			cipher.init(Cipher.ENCRYPT_MODE, key);
//			//��ü��ܿ��С����:����ǰ����Ϊ128��byte����key_size=1024 ���ܿ��СΪ127 byte,���ܺ�Ϊ128��byte;
//			//��˹���2�����ܿ飬��һ��127 byte�ڶ���Ϊ1��byte
//			int blockSize = cipher.getBlockSize();
//			int outputSize = cipher.getOutputSize(data.length);//��ü��ܿ���ܺ���С
//			int leavedSize = data.length % blockSize;
//			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
//			byte[] raw = new byte[outputSize * blocksSize];
//			int i = 0;
//			while (data.length - i * blockSize > 0) {
//				if (data.length - i * blockSize > blockSize)
//				cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
//				else
//				cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
//				//������doUpdate���������ã��鿴Դ�������ÿ��doUpdate��û��ʲôʵ�ʶ������˰�byte[]�ŵ�ByteArrayOutputStream��
//				//�������doFinal��ʱ��Ž����е�byte[]���м��ܣ����ǵ��˴�ʱ���ܿ��С�ܿ����Ѿ�������OutputSize����ֻ����dofinal������
//				i++;
//			}
//			return raw;
//		} catch (Exception e) {
//		throw new Exception(e.getMessage());
//		}
//	}
//
//	/**
//	* ����
//	* @param key ���ܵ���Կ
//	* @param raw �Ѿ����ܵ�����
//	* @return ���ܺ������
//	* @throws Exception
//	*/
//	public byte[] decrypt(Key key, byte[] raw) throws Exception {
//		try {
//			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
//			cipher.init(cipher.DECRYPT_MODE, key);
//			int blockSize = cipher.getBlockSize();
//			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
//			int j = 0;
//			while (raw.length - j * blockSize > 0) {
//				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
//				j++;
//			}
//			return bout.toByteArray();
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	}
//	
//	/**
//	 * ���ع�Կ
//	 * @return
//	 * @throws Exception 
//	 */
//	public RSAPublicKey getRSAPublicKey() throws Exception{
//		
//		//��ȡ��Կ
//		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
//		//��ȡ��Կϵ��(�ֽ�������ʽ)
//		byte[] pubModBytes = pubKey.getModulus().toByteArray();
//		//���ع�Կ����ָ��(�ֽ�������ʽ)
//		byte[] pubPubExpBytes = pubKey.getPublicExponent().toByteArray();
//		//���ɹ�Կ
//		RSAPublicKey recoveryPubKey = this.generateRSAPublicKey(pubModBytes,pubPubExpBytes);
//		return recoveryPubKey;
//	}
//	
//	/**
//	 * ��ȡ˽Կ
//	 * @return
//	 * @throws Exception 
//	 */
//	public RSAPrivateKey getRSAPrivateKey() throws Exception{
//		
//		//��ȡ˽Կ
//		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
//		//����˽Կϵ��(�ֽ�������ʽ)
//		byte[] priModBytes = priKey.getModulus().toByteArray();
//		//����˽Կר��ָ��(�ֽ�������ʽ)
//		byte[] priPriExpBytes = priKey.getPrivateExponent().toByteArray();
//		//����˽Կ
//		RSAPrivateKey recoveryPriKey = this.generateRSAPrivateKey(priModBytes,priPriExpBytes);
//		return recoveryPriKey;
//	}
//}