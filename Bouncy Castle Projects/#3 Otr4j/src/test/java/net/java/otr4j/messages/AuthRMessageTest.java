/*
 * otr4j, the open source java otr library.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package net.java.otr4j.messages;

import net.java.otr4j.api.ClientProfile;
import net.java.otr4j.api.Session.Version;
import net.java.otr4j.crypto.DHKeyPair;
import net.java.otr4j.crypto.DSAKeyPair;
import net.java.otr4j.crypto.OtrCryptoEngine4.Sigma;
import net.java.otr4j.crypto.OtrCryptoException;
import net.java.otr4j.crypto.ed448.ECDHKeyPair;
import net.java.otr4j.crypto.ed448.EdDSAKeyPair;
import net.java.otr4j.crypto.ed448.Point;
import net.java.otr4j.io.OtrInputStream;
import net.java.otr4j.io.OtrOutputStream;
import org.junit.Test;

import java.math.BigInteger;
import java.net.ProtocolException;
import java.security.SecureRandom;
import java.util.Collections;

import static net.java.otr4j.api.InstanceTag.HIGHEST_TAG;
import static net.java.otr4j.api.InstanceTag.SMALLEST_TAG;
import static net.java.otr4j.crypto.OtrCryptoEngine4.ringSign;
import static net.java.otr4j.util.SecureRandoms.randomBytes;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public final class AuthRMessageTest {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final DSAKeyPair DSA_KEYPAIR = DSAKeyPair.generateDSAKeyPair();

    private static final Point FORGING_KEY = ECDHKeyPair.generate(RANDOM).getPublicKey();

    private static final EdDSAKeyPair ED_DSA_KEYPAIR = EdDSAKeyPair.generate(RANDOM);

    private static final Point X = ECDHKeyPair.generate(RANDOM).getPublicKey();

    private static final BigInteger A = DHKeyPair.generate(RANDOM).getPublicKey();

    private static final Point FIRST_ECDH_PUBLIC_KEY = ECDHKeyPair.generate(RANDOM).getPublicKey();

    private static final BigInteger FIRST_DH_PUBLIC_KEY = DHKeyPair.generate(RANDOM).getPublicKey();

    private static final ClientProfilePayload PAYLOAD = ClientProfilePayload.signClientProfile(
            new ClientProfile(SMALLEST_TAG, ED_DSA_KEYPAIR.getPublicKey(), FORGING_KEY,
                    Collections.singleton(Version.FOUR), DSA_KEYPAIR.getPublic()),
            Long.MAX_VALUE / 1000, DSA_KEYPAIR, ED_DSA_KEYPAIR);

    @Test
    public void testConstruction() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        final AuthRMessage message = new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A,
                sigma, FIRST_ECDH_PUBLIC_KEY, FIRST_DH_PUBLIC_KEY);
        assertEquals(Version.FOUR, message.protocolVersion);
        assertEquals(SMALLEST_TAG, message.senderTag);
        assertEquals(HIGHEST_TAG, message.receiverTag);
        assertEquals(PAYLOAD, message.clientProfile);
        assertEquals(X, message.x);
        assertEquals(A, message.a);
        assertEquals(FIRST_ECDH_PUBLIC_KEY, message.ourFirstECDHPublicKey);
        assertEquals(FIRST_DH_PUBLIC_KEY, message.ourFirstDHPublicKey);
        assertEquals(sigma, message.sigma);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionIllegalProtocolVersionOne() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.ONE, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, sigma, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionIllegalProtocolVersionTwo() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.TWO, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, sigma, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionIllegalProtocolVersionThree() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.THREE, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, sigma, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullSenderTag() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, null, HIGHEST_TAG, PAYLOAD, X, A, sigma,
                FIRST_ECDH_PUBLIC_KEY, FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullReceiverTag() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, null, PAYLOAD, X, A, sigma, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullClientProfilePayload() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, null, X, A, sigma,
                FIRST_ECDH_PUBLIC_KEY, FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullX() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, null, A, sigma,
                FIRST_ECDH_PUBLIC_KEY, FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullA() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, null, sigma, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullSigma() {
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, null, FIRST_ECDH_PUBLIC_KEY,
                FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullFirstECDH() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, sigma, null, FIRST_DH_PUBLIC_KEY);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionNullFirstDH() {
        final byte[] m = randomBytes(RANDOM, new byte[150]);
        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, PAYLOAD, X, A, sigma, FIRST_ECDH_PUBLIC_KEY, null);
    }

    @Test
    public void testEncodeMessage() throws ProtocolException, OtrCryptoException, ValidationException {
//        final ClientProfilePayload payload = PAYLOAD;
        final ClientProfilePayload payload = ClientProfilePayload.readFrom(new OtrInputStream(new byte[] {0, 0, 0, 7, 0, 1, 0, 0, 1, 0, 0, 2, 0, 16, -111, 15, 55, 76, 108, 17, 33, -54, -96, 114, -45, -95, 95, -27, -80, 125, -11, 38, -50, 27, 21, -120, -126, 5, 0, -60, 59, 72, -62, 97, -113, 123, -109, 93, 29, 70, 82, -74, -80, -80, 9, 98, -56, 122, -84, 125, 43, -39, 103, 59, -10, 59, -1, -110, -105, 62, 0, 0, 3, 0, 18, 124, -12, 79, 125, -120, 99, -80, 65, -86, -96, 14, -100, 108, -77, -46, -66, -125, 56, 111, -30, -101, -96, -113, 9, -69, -39, -23, 1, 71, 94, 118, -94, 17, 112, -118, -50, 49, -84, 110, -64, 81, 39, -73, -6, -44, 117, 98, -3, 80, -89, 127, -38, 119, 118, 93, -70, -128, 0, 4, 0, 0, 0, 1, 52, 0, 5, 0, 32, -60, -101, -91, -29, 83, -9, 0, 6, 0, 0, 0, 0, 0, -128, -3, 127, 83, -127, 29, 117, 18, 41, 82, -33, 74, -100, 46, -20, -28, -25, -10, 17, -73, 82, 60, -17, 68, 0, -61, 30, 63, -128, -74, 81, 38, 105, 69, 93, 64, 34, 81, -5, 89, 61, -115, 88, -6, -65, -59, -11, -70, 48, -10, -53, -101, 85, 108, -41, -127, 59, -128, 29, 52, 111, -14, 102, 96, -73, 107, -103, 80, -91, -92, -97, -97, -24, 4, 123, 16, 34, -62, 79, -69, -87, -41, -2, -73, -58, 27, -8, 59, 87, -25, -58, -88, -90, 21, 15, 4, -5, -125, -10, -45, -59, 30, -61, 2, 53, 84, 19, 90, 22, -111, 50, -10, 117, -13, -82, 43, 97, -41, 42, -17, -14, 34, 3, 25, -99, -47, 72, 1, -57, 0, 0, 0, 20, -105, 96, 80, -113, 21, 35, 11, -52, -78, -110, -71, -126, -94, -21, -124, 11, -16, 88, 28, -11, 0, 0, 0, -128, -9, -31, -96, -123, -42, -101, 61, -34, -53, -68, -85, 92, 54, -72, 87, -71, 121, -108, -81, -69, -6, 58, -22, -126, -7, 87, 76, 11, 61, 7, -126, 103, 81, 89, 87, -114, -70, -44, 89, 79, -26, 113, 7, 16, -127, -128, -76, 73, 22, 113, 35, -24, 76, 40, 22, 19, -73, -49, 9, 50, -116, -56, -90, -31, 60, 22, 122, -117, 84, 124, -115, 40, -32, -93, -82, 30, 43, -77, -90, 117, -111, 110, -93, 127, 11, -6, 33, 53, 98, -15, -5, 98, 122, 1, 36, 59, -52, -92, -15, -66, -88, 81, -112, -119, -88, -125, -33, -31, 90, -27, -97, 6, -110, -117, 102, 94, -128, 123, 85, 37, 100, 1, 76, 59, -2, -49, 73, 42, 0, 0, 0, -128, -68, 113, 16, 43, -50, -38, 98, 37, -122, 80, -42, 119, -71, 106, 84, 87, -90, 57, 80, 114, -98, -20, 65, 32, -51, 4, -6, -109, -8, 85, -81, -55, 25, -65, -1, -71, -96, -85, -66, -82, -108, -96, 69, -97, -18, 75, -82, -115, 97, 46, 79, 36, 28, -80, 116, 53, 37, -121, 24, 79, -31, 21, -17, -87, -121, 116, -5, 62, -120, -3, -86, 79, 49, 105, 111, 43, -127, -114, -44, -127, 64, 58, -50, 2, -40, 62, 99, -7, -110, 32, 20, 98, 1, -125, 118, 20, -100, 12, 113, -73, -96, 125, -98, 35, 70, -90, -17, 107, -115, 5, 24, -74, 67, -103, -122, 68, 43, -18, -96, -14, 52, -32, -68, 17, 82, -76, -29, -89, 0, 7, 0, 0, 0, 20, 53, -28, 1, -126, 99, -39, 121, -47, -29, 50, 75, 70, 68, 95, 10, -7, -91, 64, 14, -75, 0, 0, 0, 20, 110, 91, -104, 24, -124, -25, -66, 15, 66, -124, 68, 2, -124, -6, 7, 98, 110, -100, -71, -119, 46, 69, 67, 127, -89, 42, -42, -15, 91, 58, -1, 55, 36, 48, 84, -70, -6, -33, -45, 63, 91, 40, -96, -102, -60, 106, -17, 48, -125, 53, 15, 41, 88, -48, 70, 108, 113, 77, -102, -104, -11, -35, 108, 82, -26, -87, 12, -11, 95, -5, 61, -54, 36, -61, 51, -38, -128, 66, 80, 108, -32, -72, 96, -98, 42, -114, 31, -66, -74, -85, 22, -72, 21, -12, 29, -96, -6, -116, 100, 28, -25, 19, -36, 72, -16, -41, -70, 33, 92, 42, -26, 127, 93, 42, -3, -81, 45, 78, -3, -112, 73, -126, -16, -33, 57, 44, -38, -39, -24, 51, 38, -43, 31, 0}));
//        System.err.println("Payload: " + Arrays.toString(new OtrOutputStream().write(payload).toByteArray()));
//        final Point x = X;
        final Point x = new OtrInputStream(new byte[] {-48, 9, 45, 76, -73, 125, -70, 31, 18, -95, -79, -79, 11, 107, 17, -50, 91, 10, 51, -115, 45, 106, 107, 125, -79, 18, 1, -45, -71, -86, -46, 69, -21, 93, -121, -81, -122, -1, -26, 15, 116, -113, -116, 93, -92, 37, 88, 70, -62, 28, -56, -128, 109, 98, -101, 71, 0})
                .readPoint();
//        System.err.println("X: " + Arrays.toString(x.encode()));
//        final BigInteger a = A;
        final BigInteger a = new OtrInputStream(new byte[] {0, 0, 1, -128, -82, -20, -12, -126, -127, -118, 23, -8, -126, 74, 65, 79, -19, -34, -61, -34, 87, 75, -22, 33, 112, 86, 92, 115, 32, -85, -42, 62, 59, 73, -106, 87, -82, -110, 77, -80, 69, 32, -37, -87, 35, 117, -122, -13, -114, 66, 88, -30, -50, -4, -8, -75, 81, 126, -120, -81, 45, 79, -94, 34, -46, 59, -21, -87, -77, 12, 104, -62, -40, -30, 28, 5, -91, -127, -72, -107, 65, 112, -59, -66, -124, -12, 36, -94, -93, 55, 57, 82, 82, -28, -106, 74, -115, 112, 1, -64, 27, 42, -70, -118, -2, -126, -21, 106, 96, -12, -84, 115, -16, -121, -11, 118, -30, 66, -61, 40, 43, -55, 18, 127, -94, 84, -79, -27, -64, 87, -74, 14, -89, -38, 17, 8, 41, 100, 109, -124, -70, 112, -74, -52, -58, 92, 63, 116, 82, 115, -126, 112, -29, 38, 127, -47, 11, 101, -39, -28, -43, -98, -126, -111, -10, 109, 37, 38, -2, 102, 109, -116, -80, -43, -17, 47, 12, 101, -97, -3, 43, 32, -62, 67, 3, -42, -115, 46, -111, -69, 73, -37, -79, -104, 124, -105, -36, 50, -2, 113, 110, 15, 7, -50, -75, -79, 88, 76, -37, -1, 11, -3, -112, 83, -124, 0, 60, 19, -75, 18, -11, -34, -116, 31, -110, 110, 116, -117, -15, -46, -61, 125, 38, 6, 50, -20, 47, -30, -18, 9, 125, 51, -63, 101, -66, -102, 91, -85, -100, -40, -6, -126, 104, -97, -69, -72, -128, 47, -81, 86, 71, -6, -5, 29, -93, 23, 82, -25, 62, 8, -46, -111, -39, -47, 76, -6, -71, -61, -120, 21, 70, -124, -76, 13, -116, 77, 8, -64, -93, 78, 4, 108, -35, 77, 52, -30, 37, -81, -86, 10, 84, -107, -113, 22, 97, 62, 61, -109, 123, -14, 33, -110, 89, 69, -119, 31, 45, 1, 14, -7, 123, -72, 119, 32, -90, -42, -125, 77, -73, -98, 22, 79, 125, 42, -128, 120, -9, 9, -6, 49, -16, -59, 36, 43, 52, 38, 86, -81, 77, -23, -122, 86, -38, -38, 35, 49, 54, -96, 36, 97, -123, 42, 90, -75, -9, 71, -98, 107, 45, 27, -95, 60, -8, -41, 15, -117, 20, 11, 82, -85, -102, 106, -10, -3, 79, -12, 109, -81})
                .readBigInt();
//        System.err.println("A: " + Arrays.toString(new OtrOutputStream().writeBigInt(a).toByteArray()));
//        final byte[] m = new byte[] {-90, 84, 16, 36, 45, -96, 111, -13, -53, -2, 87, -36, 34, 99, -76, -121, 126, -51, -12, -97, 34, -95, -110, -100, -126, 3, -118, -104, 80, -107, 8, -15, 92, 67, -65, 36, 53, 73, -63, -25, 15, 83, 11, 54, -77, -25, 68, -105, -46, 63, 118, -40, -87, -106, -121, -124, -55, -88, 3, 78, 36, 12, 121, -3, -52, 123, -118, 117, 26, -73, -74, -76, -16, 114, 37, 81, 117, -87, 0, 107, -59, 93, -90, 89, -74, -68, -33, 12, -111, -56, 17, -90, -87, 29, -20, -15, 14, -63, -52, 77, -124, -83, 28, -85, 124, 97, -18, -77, -111, 43, -44, 48, -119, 7, -127, -111, 49, 13, -37, 10, 4, -5, -92, -51, -1, 75, -42, 87, 73, -112, 2, 72, 119, 81, -29, -10, -118, -62, 20, -30, 32, -81, 114, 11, -80, 82, 110, 13, -79, -43};
//        final Sigma sigma = generateSigma(ED_DSA_KEYPAIR, m);
        final Sigma sigma = Sigma.readFrom(new OtrInputStream(new byte[] {-121, 11, -76, 19, -75, -82, 25, -40, -63, -122, 119, 14, -45, 127, -17, -66, -59, -110, 110, -110, 73, -114, 83, -126, 8, 62, -54, 48, -127, -94, -118, 23, 10, -40, 7, -33, -65, 117, -14, 37, -53, -112, -103, -100, 113, -85, -18, 126, -59, 84, -120, 89, 4, -117, 33, 5, 0, -35, -13, -113, -77, 11, -124, -9, -57, 51, -65, 118, -117, 80, -43, -58, -127, -35, -94, 42, -119, -56, 18, -56, 51, 85, -4, -6, -87, 100, 58, -29, -33, -9, 102, 69, -33, 3, -104, -41, 64, 108, 37, -29, 42, 3, 82, 40, -114, -99, -91, -86, -30, 56, 13, -8, 49, 0, 54, -70, 18, -39, -122, -88, -54, -29, 109, -61, -18, -92, 81, -60, 104, -40, -78, -59, -14, 101, -11, -78, -70, -38, 2, -7, -56, -84, 72, -104, 82, 113, 89, -103, 50, -12, 123, -17, -86, -90, -55, 81, -89, 79, 121, -81, -80, 18, -77, -89, 116, 37, 31, 24, 49, 6, 0, 50, -22, 38, -9, -107, -47, -18, 72, -20, -92, -26, -73, -114, 112, 95, -99, -57, 98, 60, 88, 57, -98, 2, -29, 92, 35, 27, -46, -30, -82, 60, -76, 88, 98, -26, -101, -70, -21, 30, 83, 75, 96, 69, 79, -126, 90, -12, -65, 28, -31, -37, -42, -88, -80, 96, 51, 0, 79, 85, -126, -36, -84, 80, -85, 110, -87, -122, 48, -110, 68, 76, -2, 69, 117, -118, -79, -88, 25, -86, -83, 123, -87, -48, -7, 57, 17, -101, 94, 46, -123, 24, 44, -86, -98, 96, 55, -74, 63, -82, 0, -6, -6, -122, -119, 101, 29, 0, 48, -85, 13, -44, -27, 22, 0, -50, 45, -33, -108, -35, 94, -107, -34, 86, 124, -122, -60, -37, 26, -67, 122, -38, -77, -48, 53, -22, -33, -38, -90, -20, -17, 110, 30, 31, -125, -79, 81, 96, 85, -80, -84, 4, -55, 40, -67, 16, 96, -53, -40, 97, 90, 122, 84, 126, 35, 48, 21, 19, 116, -21, 29, 0}));
//        System.err.println("Sigma: " + Arrays.toString(new OtrOutputStream().write(sigma).toByteArray()));
//        final Point firstECDHPublicKey = FIRST_ECDH_PUBLIC_KEY;
        final Point firstECDHPublicKey = new OtrInputStream(new byte[] {66, 71, 102, -98, -63, -62, -19, -76, -76, 0, 64, 84, -104, 29, 94, 3, -85, -85, -50, -23, 99, 68, -40, 26, 56, 32, -15, -95, 81, -19, 101, -40, 106, 101, -49, 90, 50, -58, -119, -28, 80, 57, 38, -17, 90, -22, -107, -97, 28, 67, 95, 64, -95, 72, 43, -29, -128})
                .readPoint();
//        System.err.println("First ECDH: " + Arrays.toString(new OtrOutputStream().writePoint(FIRST_ECDH_PUBLIC_KEY).toByteArray()));
//        final BigInteger firstDHPublicKey = FIRST_DH_PUBLIC_KEY;
        final BigInteger firstDHPublicKey = new OtrInputStream(new byte[] {0, 0, 1, -128, -17, -126, -7, 117, -126, 44, -12, -15, -85, -73, -24, 14, -61, -117, -90, 105, -100, 55, -7, -95, 105, -85, -62, 40, -97, -18, 75, -97, -49, 123, 5, 46, -108, 73, -89, -98, 50, 19, 74, 122, 87, 124, -6, -13, -74, -87, 105, -82, 46, 50, -19, 32, 124, -60, -11, 10, 43, 72, 25, 112, 92, -95, 100, -76, -98, -36, 116, 66, -121, -32, 105, -105, 53, -65, 102, -3, -115, 100, -59, 16, -103, -95, -20, -57, -109, 49, -62, -73, 103, -47, 34, -98, 39, 62, -64, -7, 40, -97, 101, 86, 72, -100, 42, -33, 119, 69, -125, 83, 9, -99, -32, -56, -35, 100, 74, -102, -118, -88, 76, 90, 30, -34, -38, -121, -87, 113, -102, -33, 13, -2, -125, 16, -61, -44, 2, 104, -96, 53, -75, 43, 15, 3, 87, 105, -56, 81, -102, 64, -45, 8, -107, 23, 7, -41, -13, -120, -89, 31, 27, 107, -10, -3, 42, 58, 109, -100, -67, 72, 78, 125, 103, 38, -71, 77, -73, -122, -84, -39, -100, 46, 4, 91, 41, 108, -102, 4, 67, -32, 7, 17, 58, 100, 118, -121, -6, -14, -44, 62, -62, 32, 13, -122, 32, 30, -125, 87, 51, 113, -27, 54, 22, -103, -48, 54, 26, 82, 47, 82, -108, -125, -16, -6, -121, -56, -106, 61, -16, 59, -16, -119, -120, 18, -2, 106, 45, 55, -13, 9, -45, -25, 115, 18, 77, 1, -74, -76, -53, 96, 33, -30, -100, -21, 33, 43, 16, -110, 44, 56, 124, -44, -39, 47, 101, -20, 14, 87, 106, -110, 60, -61, 75, -30, -74, -119, -88, -61, -39, -95, -60, -29, -97, 71, -29, 60, 33, -8, 15, 57, -24, -67, -106, 62, -24, 123, 108, 99, 19, 14, -117, -112, 34, -48, 54, -77, -95, 99, -103, -72, 82, -118, -1, -24, 17, 28, 21, -39, 27, 101, 1, 104, 40, 76, 70, 13, -111, 124, 118, 118, -55, 125, 51, -102, -95, 31, -68, 36, -82, -38, 121, -33, 37, -108, -17, -40, 66, -73, -85, -82, 65, -21, -69, -86, 123, -38, 116, -14, 39, 109, -62, -1, 66, -114, 79, 84, -39, 97, 61, 71, 77, 41, -104, 79, 105, -84, -69, 123, 14, 66, 126, 77, -35, -57, -48, 119})
                .readBigInt();
//        System.err.println("First DH: " + Arrays.toString(new OtrOutputStream().writeBigInt(firstDHPublicKey).toByteArray()));
        final AuthRMessage message = new AuthRMessage(Version.FOUR, SMALLEST_TAG, HIGHEST_TAG, payload, x, a,
                sigma, firstECDHPublicKey, firstDHPublicKey);
//        System.err.println("Result: " + Arrays.toString(new OtrOutputStream().write(message).toByteArray()));
        final byte[] expected = new byte[] {0, 4, 54, 0, 0, 1, 0, -1, -1, -1, -1, 0, 0, 0, 7, 0, 1, 0, 0, 1, 0, 0, 2, 0, 16, -111, 15, 55, 76, 108, 17, 33, -54, -96, 114, -45, -95, 95, -27, -80, 125, -11, 38, -50, 27, 21, -120, -126, 5, 0, -60, 59, 72, -62, 97, -113, 123, -109, 93, 29, 70, 82, -74, -80, -80, 9, 98, -56, 122, -84, 125, 43, -39, 103, 59, -10, 59, -1, -110, -105, 62, 0, 0, 3, 0, 18, 124, -12, 79, 125, -120, 99, -80, 65, -86, -96, 14, -100, 108, -77, -46, -66, -125, 56, 111, -30, -101, -96, -113, 9, -69, -39, -23, 1, 71, 94, 118, -94, 17, 112, -118, -50, 49, -84, 110, -64, 81, 39, -73, -6, -44, 117, 98, -3, 80, -89, 127, -38, 119, 118, 93, -70, -128, 0, 4, 0, 0, 0, 1, 52, 0, 5, 0, 32, -60, -101, -91, -29, 83, -9, 0, 6, 0, 0, 0, 0, 0, -128, -3, 127, 83, -127, 29, 117, 18, 41, 82, -33, 74, -100, 46, -20, -28, -25, -10, 17, -73, 82, 60, -17, 68, 0, -61, 30, 63, -128, -74, 81, 38, 105, 69, 93, 64, 34, 81, -5, 89, 61, -115, 88, -6, -65, -59, -11, -70, 48, -10, -53, -101, 85, 108, -41, -127, 59, -128, 29, 52, 111, -14, 102, 96, -73, 107, -103, 80, -91, -92, -97, -97, -24, 4, 123, 16, 34, -62, 79, -69, -87, -41, -2, -73, -58, 27, -8, 59, 87, -25, -58, -88, -90, 21, 15, 4, -5, -125, -10, -45, -59, 30, -61, 2, 53, 84, 19, 90, 22, -111, 50, -10, 117, -13, -82, 43, 97, -41, 42, -17, -14, 34, 3, 25, -99, -47, 72, 1, -57, 0, 0, 0, 20, -105, 96, 80, -113, 21, 35, 11, -52, -78, -110, -71, -126, -94, -21, -124, 11, -16, 88, 28, -11, 0, 0, 0, -128, -9, -31, -96, -123, -42, -101, 61, -34, -53, -68, -85, 92, 54, -72, 87, -71, 121, -108, -81, -69, -6, 58, -22, -126, -7, 87, 76, 11, 61, 7, -126, 103, 81, 89, 87, -114, -70, -44, 89, 79, -26, 113, 7, 16, -127, -128, -76, 73, 22, 113, 35, -24, 76, 40, 22, 19, -73, -49, 9, 50, -116, -56, -90, -31, 60, 22, 122, -117, 84, 124, -115, 40, -32, -93, -82, 30, 43, -77, -90, 117, -111, 110, -93, 127, 11, -6, 33, 53, 98, -15, -5, 98, 122, 1, 36, 59, -52, -92, -15, -66, -88, 81, -112, -119, -88, -125, -33, -31, 90, -27, -97, 6, -110, -117, 102, 94, -128, 123, 85, 37, 100, 1, 76, 59, -2, -49, 73, 42, 0, 0, 0, -128, -68, 113, 16, 43, -50, -38, 98, 37, -122, 80, -42, 119, -71, 106, 84, 87, -90, 57, 80, 114, -98, -20, 65, 32, -51, 4, -6, -109, -8, 85, -81, -55, 25, -65, -1, -71, -96, -85, -66, -82, -108, -96, 69, -97, -18, 75, -82, -115, 97, 46, 79, 36, 28, -80, 116, 53, 37, -121, 24, 79, -31, 21, -17, -87, -121, 116, -5, 62, -120, -3, -86, 79, 49, 105, 111, 43, -127, -114, -44, -127, 64, 58, -50, 2, -40, 62, 99, -7, -110, 32, 20, 98, 1, -125, 118, 20, -100, 12, 113, -73, -96, 125, -98, 35, 70, -90, -17, 107, -115, 5, 24, -74, 67, -103, -122, 68, 43, -18, -96, -14, 52, -32, -68, 17, 82, -76, -29, -89, 0, 7, 0, 0, 0, 20, 53, -28, 1, -126, 99, -39, 121, -47, -29, 50, 75, 70, 68, 95, 10, -7, -91, 64, 14, -75, 0, 0, 0, 20, 110, 91, -104, 24, -124, -25, -66, 15, 66, -124, 68, 2, -124, -6, 7, 98, 110, -100, -71, -119, 46, 69, 67, 127, -89, 42, -42, -15, 91, 58, -1, 55, 36, 48, 84, -70, -6, -33, -45, 63, 91, 40, -96, -102, -60, 106, -17, 48, -125, 53, 15, 41, 88, -48, 70, 108, 113, 77, -102, -104, -11, -35, 108, 82, -26, -87, 12, -11, 95, -5, 61, -54, 36, -61, 51, -38, -128, 66, 80, 108, -32, -72, 96, -98, 42, -114, 31, -66, -74, -85, 22, -72, 21, -12, 29, -96, -6, -116, 100, 28, -25, 19, -36, 72, -16, -41, -70, 33, 92, 42, -26, 127, 93, 42, -3, -81, 45, 78, -3, -112, 73, -126, -16, -33, 57, 44, -38, -39, -24, 51, 38, -43, 31, 0, -48, 9, 45, 76, -73, 125, -70, 31, 18, -95, -79, -79, 11, 107, 17, -50, 91, 10, 51, -115, 45, 106, 107, 125, -79, 18, 1, -45, -71, -86, -46, 69, -21, 93, -121, -81, -122, -1, -26, 15, 116, -113, -116, 93, -92, 37, 88, 70, -62, 28, -56, -128, 109, 98, -101, 71, 0, 0, 0, 1, -128, -82, -20, -12, -126, -127, -118, 23, -8, -126, 74, 65, 79, -19, -34, -61, -34, 87, 75, -22, 33, 112, 86, 92, 115, 32, -85, -42, 62, 59, 73, -106, 87, -82, -110, 77, -80, 69, 32, -37, -87, 35, 117, -122, -13, -114, 66, 88, -30, -50, -4, -8, -75, 81, 126, -120, -81, 45, 79, -94, 34, -46, 59, -21, -87, -77, 12, 104, -62, -40, -30, 28, 5, -91, -127, -72, -107, 65, 112, -59, -66, -124, -12, 36, -94, -93, 55, 57, 82, 82, -28, -106, 74, -115, 112, 1, -64, 27, 42, -70, -118, -2, -126, -21, 106, 96, -12, -84, 115, -16, -121, -11, 118, -30, 66, -61, 40, 43, -55, 18, 127, -94, 84, -79, -27, -64, 87, -74, 14, -89, -38, 17, 8, 41, 100, 109, -124, -70, 112, -74, -52, -58, 92, 63, 116, 82, 115, -126, 112, -29, 38, 127, -47, 11, 101, -39, -28, -43, -98, -126, -111, -10, 109, 37, 38, -2, 102, 109, -116, -80, -43, -17, 47, 12, 101, -97, -3, 43, 32, -62, 67, 3, -42, -115, 46, -111, -69, 73, -37, -79, -104, 124, -105, -36, 50, -2, 113, 110, 15, 7, -50, -75, -79, 88, 76, -37, -1, 11, -3, -112, 83, -124, 0, 60, 19, -75, 18, -11, -34, -116, 31, -110, 110, 116, -117, -15, -46, -61, 125, 38, 6, 50, -20, 47, -30, -18, 9, 125, 51, -63, 101, -66, -102, 91, -85, -100, -40, -6, -126, 104, -97, -69, -72, -128, 47, -81, 86, 71, -6, -5, 29, -93, 23, 82, -25, 62, 8, -46, -111, -39, -47, 76, -6, -71, -61, -120, 21, 70, -124, -76, 13, -116, 77, 8, -64, -93, 78, 4, 108, -35, 77, 52, -30, 37, -81, -86, 10, 84, -107, -113, 22, 97, 62, 61, -109, 123, -14, 33, -110, 89, 69, -119, 31, 45, 1, 14, -7, 123, -72, 119, 32, -90, -42, -125, 77, -73, -98, 22, 79, 125, 42, -128, 120, -9, 9, -6, 49, -16, -59, 36, 43, 52, 38, 86, -81, 77, -23, -122, 86, -38, -38, 35, 49, 54, -96, 36, 97, -123, 42, 90, -75, -9, 71, -98, 107, 45, 27, -95, 60, -8, -41, 15, -117, 20, 11, 82, -85, -102, 106, -10, -3, 79, -12, 109, -81, -121, 11, -76, 19, -75, -82, 25, -40, -63, -122, 119, 14, -45, 127, -17, -66, -59, -110, 110, -110, 73, -114, 83, -126, 8, 62, -54, 48, -127, -94, -118, 23, 10, -40, 7, -33, -65, 117, -14, 37, -53, -112, -103, -100, 113, -85, -18, 126, -59, 84, -120, 89, 4, -117, 33, 5, 0, -35, -13, -113, -77, 11, -124, -9, -57, 51, -65, 118, -117, 80, -43, -58, -127, -35, -94, 42, -119, -56, 18, -56, 51, 85, -4, -6, -87, 100, 58, -29, -33, -9, 102, 69, -33, 3, -104, -41, 64, 108, 37, -29, 42, 3, 82, 40, -114, -99, -91, -86, -30, 56, 13, -8, 49, 0, 54, -70, 18, -39, -122, -88, -54, -29, 109, -61, -18, -92, 81, -60, 104, -40, -78, -59, -14, 101, -11, -78, -70, -38, 2, -7, -56, -84, 72, -104, 82, 113, 89, -103, 50, -12, 123, -17, -86, -90, -55, 81, -89, 79, 121, -81, -80, 18, -77, -89, 116, 37, 31, 24, 49, 6, 0, 50, -22, 38, -9, -107, -47, -18, 72, -20, -92, -26, -73, -114, 112, 95, -99, -57, 98, 60, 88, 57, -98, 2, -29, 92, 35, 27, -46, -30, -82, 60, -76, 88, 98, -26, -101, -70, -21, 30, 83, 75, 96, 69, 79, -126, 90, -12, -65, 28, -31, -37, -42, -88, -80, 96, 51, 0, 79, 85, -126, -36, -84, 80, -85, 110, -87, -122, 48, -110, 68, 76, -2, 69, 117, -118, -79, -88, 25, -86, -83, 123, -87, -48, -7, 57, 17, -101, 94, 46, -123, 24, 44, -86, -98, 96, 55, -74, 63, -82, 0, -6, -6, -122, -119, 101, 29, 0, 48, -85, 13, -44, -27, 22, 0, -50, 45, -33, -108, -35, 94, -107, -34, 86, 124, -122, -60, -37, 26, -67, 122, -38, -77, -48, 53, -22, -33, -38, -90, -20, -17, 110, 30, 31, -125, -79, 81, 96, 85, -80, -84, 4, -55, 40, -67, 16, 96, -53, -40, 97, 90, 122, 84, 126, 35, 48, 21, 19, 116, -21, 29, 0, 66, 71, 102, -98, -63, -62, -19, -76, -76, 0, 64, 84, -104, 29, 94, 3, -85, -85, -50, -23, 99, 68, -40, 26, 56, 32, -15, -95, 81, -19, 101, -40, 106, 101, -49, 90, 50, -58, -119, -28, 80, 57, 38, -17, 90, -22, -107, -97, 28, 67, 95, 64, -95, 72, 43, -29, -128, 0, 0, 1, -128, -17, -126, -7, 117, -126, 44, -12, -15, -85, -73, -24, 14, -61, -117, -90, 105, -100, 55, -7, -95, 105, -85, -62, 40, -97, -18, 75, -97, -49, 123, 5, 46, -108, 73, -89, -98, 50, 19, 74, 122, 87, 124, -6, -13, -74, -87, 105, -82, 46, 50, -19, 32, 124, -60, -11, 10, 43, 72, 25, 112, 92, -95, 100, -76, -98, -36, 116, 66, -121, -32, 105, -105, 53, -65, 102, -3, -115, 100, -59, 16, -103, -95, -20, -57, -109, 49, -62, -73, 103, -47, 34, -98, 39, 62, -64, -7, 40, -97, 101, 86, 72, -100, 42, -33, 119, 69, -125, 83, 9, -99, -32, -56, -35, 100, 74, -102, -118, -88, 76, 90, 30, -34, -38, -121, -87, 113, -102, -33, 13, -2, -125, 16, -61, -44, 2, 104, -96, 53, -75, 43, 15, 3, 87, 105, -56, 81, -102, 64, -45, 8, -107, 23, 7, -41, -13, -120, -89, 31, 27, 107, -10, -3, 42, 58, 109, -100, -67, 72, 78, 125, 103, 38, -71, 77, -73, -122, -84, -39, -100, 46, 4, 91, 41, 108, -102, 4, 67, -32, 7, 17, 58, 100, 118, -121, -6, -14, -44, 62, -62, 32, 13, -122, 32, 30, -125, 87, 51, 113, -27, 54, 22, -103, -48, 54, 26, 82, 47, 82, -108, -125, -16, -6, -121, -56, -106, 61, -16, 59, -16, -119, -120, 18, -2, 106, 45, 55, -13, 9, -45, -25, 115, 18, 77, 1, -74, -76, -53, 96, 33, -30, -100, -21, 33, 43, 16, -110, 44, 56, 124, -44, -39, 47, 101, -20, 14, 87, 106, -110, 60, -61, 75, -30, -74, -119, -88, -61, -39, -95, -60, -29, -97, 71, -29, 60, 33, -8, 15, 57, -24, -67, -106, 62, -24, 123, 108, 99, 19, 14, -117, -112, 34, -48, 54, -77, -95, 99, -103, -72, 82, -118, -1, -24, 17, 28, 21, -39, 27, 101, 1, 104, 40, 76, 70, 13, -111, 124, 118, 118, -55, 125, 51, -102, -95, 31, -68, 36, -82, -38, 121, -33, 37, -108, -17, -40, 66, -73, -85, -82, 65, -21, -69, -86, 123, -38, 116, -14, 39, 109, -62, -1, 66, -114, 79, 84, -39, 97, 61, 71, 77, 41, -104, 79, 105, -84, -69, 123, 14, 66, 126, 77, -35, -57, -48, 119};
        assertArrayEquals(expected, new OtrOutputStream().write(message).toByteArray());
    }

    private static Sigma generateSigma(final EdDSAKeyPair keypair, final byte[] message) {
        final ECDHKeyPair pair1 = ECDHKeyPair.generate(RANDOM);
        final ECDHKeyPair pair2 = ECDHKeyPair.generate(RANDOM);
        return ringSign(RANDOM, keypair, keypair.getPublicKey(), pair1.getPublicKey(), pair2.getPublicKey(), message);
    }
}