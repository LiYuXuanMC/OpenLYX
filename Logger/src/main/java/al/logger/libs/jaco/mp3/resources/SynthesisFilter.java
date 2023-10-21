/*
 * 11/19/04 1.0 moved to LGPL.
 * 
 * 04/01/00 Fixes for running under build 23xx Microsoft JVM. mdm.
 * 
 * 19/12/99 Performance improvements to compute_pcm_samples().  
 *			Mat McGowan. mdm@techie.com. 
 *
 * 16/02/99 Java Conversion by E.B , javalayer@javazoom.net
 *
 *  @(#) synthesis_filter.h 1.8, last edit: 6/15/94 16:52:00
 *  @(#) Copyright (C) 1993, 1994 Tobias Bading (bading@cs.tu-berlin.de)
 *  @(#) Berlin University of Technology
 *
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */
package al.logger.libs.jaco.mp3.resources;

import java.math.BigDecimal;

/**
 * A class for the synthesis filter bank.
 * This class does a fast downsampling from 32, 44.1 or 48 kHz to 8 kHz, if ULAW is defined.
 * Frequencies above 4 kHz are removed by ignoring higher subbands.
 */
final class SynthesisFilter
{
  private float[] 			 v1;
  private float[]		 	 v2;
  private float[]			 actual_v;			// v1 or v2
  private int 			 	 actual_write_pos;	// 0-15
  private float[]			 samples;			// 32 new subband samples
  private int				 channel;
  private float 			 scalefactor;
  private float[]			 eq;
	
	/**
	 * Quality value for controlling CPU usage/quality tradeoff. 
	 */
	/*
	private int				quality;
	
	private int				v_inc;
	
	
	
	public static final int	HIGH_QUALITY = 1;
	public static final int MEDIUM_QUALITY = 2;
	public static final int LOW_QUALITY = 4;
	*/
	
  /**
   * Contructor.
   * The scalefactor scales the calculated float pcm samples to short values
   * (raw pcm samples are in [-1.0, 1.0], if no violations occur).
   */
  public SynthesisFilter(int channelnumber, float factor, float[] eq0)
  {  	 
	  if (d==null)
	  {
			d = load_d();
			d16 = splitArray(d, 16);
	  }
	  
	  v1 = new float[512];
	 v2 = new float[512];
	 samples = new float[32];
     channel = channelnumber;
	 scalefactor = factor;
	 setEQ(eq);	 
	 //setQuality(HIGH_QUALITY);
	 
     reset();
  }
  
  public void setEQ(float[] eq0)
  {
	 this.eq = eq0;	 
	 if (eq==null)
	 {
		 eq = new float[32];
		 for (int i=0; i<32; i++)
			 eq[i] = 1.0f;
	 }
	 if (eq.length<32)
	 {
		throw new IllegalArgumentException("eq0");	 
	 }
	  
  }
  
	/*
	private void setQuality(int quality0)
	{
	  	switch (quality0)
	  	{		
		case HIGH_QUALITY:
		case MEDIUM_QUALITY:
		case LOW_QUALITY:						  
			v_inc = 16 * quality0;			
			quality = quality0;
			break;	
		default :
			throw new IllegalArgumentException("Unknown quality value");
	  	}				
	}
	
	public int getQuality()
	{
		return quality;	
	}
	*/
  
  /**
   * Reset the synthesis filter.
   */
  public void reset()
  {
     //float[] floatp;
	 // float[] floatp2;

     // initialize v1[] and v2[]:
     //for (floatp = v1 + 512, floatp2 = v2 + 512; floatp > v1; )
	 //   *--floatp = *--floatp2 = 0.0;
	 for (int p=0;p<512;p++) 
		 v1[p] = v2[p] = 0.0f;

     // initialize samples[]:
     //for (floatp = samples + 32; floatp > samples; )
	 //  *--floatp = 0.0;
	 for (int p2=0;p2<32;p2++) 
		 samples[p2] = 0.0f;

     actual_v = v1;
     actual_write_pos = 15;
  }


  /**
   * Inject Sample.
   */
  public void input_sample(float sample, int subbandnumber)
  {	 	 		  
	  samples[subbandnumber] = eq[subbandnumber]*sample;
  }

  public void input_samples(float[] s)
  {
	  for (int i=31; i>=0; i--)
	  {		
		 samples[i] = s[i]*eq[i];
	  }
  }
  
  /**
   * Compute new values via a fast cosine transform.
   */
  private void compute_new_v()
  {
	// p is fully initialized from x1
	 //float[] p = _p;
	 // pp is fully initialized from p
	 //float[] pp = _pp; 
	  
	 //float[] new_v = _new_v;
	  
  	//float[] new_v = new float[32]; // new V[0-15] and V[33-48] of Figure 3-A.2 in ISO DIS 11172-3
	//float[] p = new float[16];
	//float[] pp = new float[16];
	  
	 /*
	 for (int i=31; i>=0; i--)
	 {
		 new_v[i] = 0.0f;
	 }
	  */
	  
	float new_v0, new_v1, new_v2, new_v3, new_v4, new_v5, new_v6, new_v7, new_v8, new_v9;
	float new_v10, new_v11, new_v12, new_v13, new_v14, new_v15, new_v16, new_v17, new_v18, new_v19;
	float new_v20, new_v21, new_v22, new_v23, new_v24, new_v25, new_v26, new_v27, new_v28, new_v29;
	float new_v30, new_v31;
	  
	new_v0 = new_v1 = new_v2 = new_v3 = new_v4 = new_v5 = new_v6 = new_v7 = new_v8 = new_v9 = 
	new_v10 = new_v11 = new_v12 = new_v13 = new_v14 = new_v15 = new_v16 = new_v17 = new_v18 = new_v19 = 
	new_v20 = new_v21 = new_v22 = new_v23 = new_v24 = new_v25 = new_v26 = new_v27 = new_v28 = new_v29 = 
	new_v30 = new_v31 = 0.0f;
	
	
//	float[] new_v = new float[32]; // new V[0-15] and V[33-48] of Figure 3-A.2 in ISO DIS 11172-3
//	float[] p = new float[16];
//	float[] pp = new float[16];

    float[] s = samples;
	
	float s0 = s[0];
	float s1 = s[1];
	float s2 = s[2];
	float s3 = s[3];
	float s4 = s[4];
	float s5 = s[5];
	float s6 = s[6];
	float s7 = s[7];
	float s8 = s[8];
	float s9 = s[9];
	float s10 = s[10];	
	float s11 = s[11];
	float s12 = s[12];
	float s13 = s[13];
	float s14 = s[14];
	float s15 = s[15];
	float s16 = s[16];
	float s17 = s[17];
	float s18 = s[18];
	float s19 = s[19];
	float s20 = s[20];	
	float s21 = s[21];
	float s22 = s[22];
	float s23 = s[23];
	float s24 = s[24];
	float s25 = s[25];
	float s26 = s[26];
	float s27 = s[27];
	float s28 = s[28];
	float s29 = s[29];
	float s30 = s[30];	
	float s31 = s[31];
		
	float p0 = s0 + s31;
	float p1 = s1 + s30;
	float p2 = s2 + s29;
	float p3 = s3 + s28;
	float p4 = s4 + s27;
	float p5 = s5 + s26;
	float p6 = s6 + s25;
	float p7 = s7 + s24;
	float p8 = s8 + s23;
	float p9 = s9 + s22;
	float p10 = s10 + s21;
	float p11 = s11 + s20;
	float p12 = s12 + s19;
	float p13 = s13 + s18;
	float p14 = s14 + s17;
	float p15 = s15 + s16;
	
	float pp0 = p0 + p15;
	float pp1 = p1 + p14;
	float pp2 = p2 + p13;
	float pp3 = p3 + p12;
	float pp4 = p4 + p11;
	float pp5 = p5 + p10;
	float pp6 = p6 + p9;
	float pp7 = p7 + p8;
	float pp8 = (p0 - p15) * cos1_32;
	float pp9 = (p1 - p14) * cos3_32;
	float pp10 = (p2 - p13) * cos5_32;
	float pp11 = (p3 - p12) * cos7_32;
	float pp12 = (p4 - p11) * cos9_32;
	float pp13 = (p5 - p10) * cos11_32;
	float pp14 = (p6 - p9) * cos13_32;
	float pp15 = (p7 - p8) * cos15_32;

	p0 = pp0 + pp7;
	p1 = pp1 + pp6;
	p2 = pp2 + pp5;
	p3 = pp3 + pp4;
	p4 = (pp0 - pp7) * cos1_16;
	p5 = (pp1 - pp6) * cos3_16;
	p6 = (pp2 - pp5) * cos5_16;
	p7 = (pp3 - pp4) * cos7_16;
	p8 = pp8 + pp15;
	p9 = pp9 + pp14;
	p10 = pp10 + pp13;
	p11 = pp11 + pp12;
	p12 = (pp8 - pp15) * cos1_16;
	p13 = (pp9 - pp14) * cos3_16;
	p14 = (pp10 - pp13) * cos5_16;
	p15 = (pp11 - pp12) * cos7_16;
	

	pp0 = p0 + p3;
	pp1 = p1 + p2;
	pp2 = (p0 - p3) * cos1_8;
	pp3 = (p1 - p2) * cos3_8;
	pp4 = p4 + p7;
	pp5 = p5 + p6;
	pp6 = (p4 - p7) * cos1_8;
	pp7 = (p5 - p6) * cos3_8;
	pp8 = p8 + p11;
	pp9 = p9 + p10;
	pp10 = (p8 - p11) * cos1_8;
	pp11 = (p9 - p10) * cos3_8;
	pp12 = p12 + p15;
	pp13 = p13 + p14;
	pp14 = (p12 - p15) * cos1_8;
	pp15 = (p13 - p14) * cos3_8;

	p0 = pp0 + pp1;
	p1 = (pp0 - pp1) * cos1_4;
	p2 = pp2 + pp3;
	p3 = (pp2 - pp3) * cos1_4;
	p4 = pp4 + pp5;
	p5 = (pp4 - pp5) * cos1_4;
	p6 = pp6 + pp7;
	p7 = (pp6 - pp7) * cos1_4;
	p8 = pp8 + pp9;
	p9 = (pp8 - pp9) * cos1_4;
	p10 = pp10 + pp11;
	p11 = (pp10 - pp11) * cos1_4;
	p12 = pp12 + pp13;
	p13 = (pp12 - pp13) * cos1_4;
	p14 = pp14 + pp15;
	p15 = (pp14 - pp15) * cos1_4;

	// this is pretty insane coding
	float tmp1;
	new_v19/*36-17*/ = -(new_v4 = (new_v12 = p7) + p5) - p6;
	new_v27/*44-17*/ = -p6 - p7 - p4;
	new_v6 = (new_v10 = (new_v14 = p15) + p11) + p13;
	new_v17/*34-17*/ = -(new_v2 = p15 + p13 + p9) - p14;
	new_v21/*38-17*/ = (tmp1 = -p14 - p15 - p10 - p11) - p13;
	new_v29/*46-17*/ = -p14 - p15 - p12 - p8;
	new_v25/*42-17*/ = tmp1 - p12;
	new_v31/*48-17*/ = -p0;
	new_v0 = p1;
	new_v23/*40-17*/ = -(new_v8 = p3) - p2;
	
	p0 = (s0 - s31) * cos1_64;
	p1 = (s1 - s30) * cos3_64;
	p2 = (s2 - s29) * cos5_64;
	p3 = (s3 - s28) * cos7_64;
	p4 = (s4 - s27) * cos9_64;
	p5 = (s5 - s26) * cos11_64;
	p6 = (s6 - s25) * cos13_64;
	p7 = (s7 - s24) * cos15_64;
	p8 = (s8 - s23) * cos17_64;
	p9 = (s9 - s22) * cos19_64;
	p10 = (s10 - s21) * cos21_64;
	p11 = (s11 - s20) * cos23_64;
	p12 = (s12 - s19) * cos25_64;
	p13 = (s13 - s18) * cos27_64;
	p14 = (s14 - s17) * cos29_64;
	p15 = (s15 - s16) * cos31_64;

	
	pp0 = p0 + p15;
	pp1 = p1 + p14;
	pp2 = p2 + p13;
	pp3 = p3 + p12;
	pp4 = p4 + p11;
	pp5 = p5 + p10;
	pp6 = p6 + p9;
	pp7 = p7 + p8;
	pp8 = (p0 - p15) * cos1_32;
	pp9 = (p1 - p14) * cos3_32;
	pp10 = (p2 - p13) * cos5_32;
	pp11 = (p3 - p12) * cos7_32;
	pp12 = (p4 - p11) * cos9_32;
	pp13 = (p5 - p10) * cos11_32;
	pp14 = (p6 - p9) * cos13_32;
	pp15 = (p7 - p8) * cos15_32;
	

	p0 = pp0 + pp7;
	p1 = pp1 + pp6;
	p2 = pp2 + pp5;
	p3 = pp3 + pp4;
	p4 = (pp0 - pp7) * cos1_16;
	p5 = (pp1 - pp6) * cos3_16;
	p6 = (pp2 - pp5) * cos5_16;
	p7 = (pp3 - pp4) * cos7_16;
	p8 = pp8 + pp15;
	p9 = pp9 + pp14;
	p10 = pp10 + pp13;
	p11 = pp11 + pp12;
	p12 = (pp8 - pp15) * cos1_16;
	p13 = (pp9 - pp14) * cos3_16;
	p14 = (pp10 - pp13) * cos5_16;
	p15 = (pp11 - pp12) * cos7_16;


	pp0 = p0 + p3;
	pp1 = p1 + p2;
	pp2 = (p0 - p3) * cos1_8;
	pp3 = (p1 - p2) * cos3_8;
	pp4 = p4 + p7;
	pp5 = p5 + p6;
	pp6 = (p4 - p7) * cos1_8;
	pp7 = (p5 - p6) * cos3_8;
	pp8 = p8 + p11;
	pp9 = p9 + p10;
	pp10 = (p8 - p11) * cos1_8;
	pp11 = (p9 - p10) * cos3_8;
	pp12 = p12 + p15;
	pp13 = p13 + p14;
	pp14 = (p12 - p15) * cos1_8;
	pp15 = (p13 - p14) * cos3_8;

	
	p0 = pp0 + pp1;
	p1 = (pp0 - pp1) * cos1_4;
	p2 = pp2 + pp3;
	p3 = (pp2 - pp3) * cos1_4;
	p4 = pp4 + pp5;
	p5 = (pp4 - pp5) * cos1_4;
	p6 = pp6 + pp7;
	p7 = (pp6 - pp7) * cos1_4;
	p8 = pp8 + pp9;
	p9 = (pp8 - pp9) * cos1_4;
	p10 = pp10 + pp11;
	p11 = (pp10 - pp11) * cos1_4;
	p12 = pp12 + pp13;
	p13 = (pp12 - pp13) * cos1_4;
	p14 = pp14 + pp15;
	p15 = (pp14 - pp15) * cos1_4;
	

	// manually doing something that a compiler should handle sucks
	// coding like this is hard to read
	float tmp2;
	new_v5 = (new_v11 = (new_v13 = (new_v15 = p15) + p7) + p11)
							+ p5 + p13;
	new_v7 = (new_v9 = p15 + p11 + p3) + p13;
	new_v16/*33-17*/ = -(new_v1 = (tmp1 = p13 + p15 + p9) + p1) - p14;
	new_v18/*35-17*/ = -(new_v3 = tmp1 + p5 + p7) - p6 - p14;

	new_v22/*39-17*/ = (tmp1 = -p10 - p11 - p14 - p15)
									- p13 - p2 - p3;
	new_v20/*37-17*/ = tmp1 - p13 - p5 - p6 - p7;
	new_v24/*41-17*/ = tmp1 - p12 - p2 - p3;
	new_v26/*43-17*/ = tmp1 - p12 - (tmp2 = p4 + p6 + p7);
	new_v30/*47-17*/ = (tmp1 = -p8 - p12 - p14 - p15) - p0;
	new_v28/*45-17*/ = tmp1 - tmp2;

	// insert V[0-15] (== new_v[0-15]) into actual v:	
	// float[] x2 = actual_v + actual_write_pos;
	float dest[] = actual_v;
	
	int pos = actual_write_pos;
	
	dest[0 + pos] = new_v0;
	dest[16 + pos] = new_v1;
	dest[32 + pos] = new_v2;
	dest[48 + pos] = new_v3;
	dest[64 + pos] = new_v4;
	dest[80 + pos] = new_v5;
	dest[96 + pos] = new_v6;
	dest[112 + pos] = new_v7;
	dest[128 + pos] = new_v8;
	dest[144 + pos] = new_v9;
	dest[160 + pos] = new_v10;
	dest[176 + pos] = new_v11;
	dest[192 + pos] = new_v12;
	dest[208 + pos] = new_v13;
	dest[224 + pos] = new_v14;
	dest[240 + pos] = new_v15;

	// V[16] is always 0.0:
	dest[256 + pos] = 0.0f;

	// insert V[17-31] (== -new_v[15-1]) into actual v:
	dest[272 + pos] = -new_v15;
	dest[288 + pos] = -new_v14;
	dest[304 + pos] = -new_v13;
	dest[320 + pos] = -new_v12;
	dest[336 + pos] = -new_v11;
	dest[352 + pos] = -new_v10;
	dest[368 + pos] = -new_v9;
	dest[384 + pos] = -new_v8;
	dest[400 + pos] = -new_v7;
	dest[416 + pos] = -new_v6;
	dest[432 + pos] = -new_v5;
	dest[448 + pos] = -new_v4;
	dest[464 + pos] = -new_v3;
	dest[480 + pos] = -new_v2;
	dest[496 + pos] = -new_v1;

	// insert V[32] (== -new_v[0]) into other v:
	dest = (actual_v==v1) ? v2 : v1;
	
	dest[0 + pos] = -new_v0;
	// insert V[33-48] (== new_v[16-31]) into other v:
	dest[16 + pos] = new_v16;
	dest[32 + pos] = new_v17;
	dest[48 + pos] = new_v18;
	dest[64 + pos] = new_v19;
	dest[80 + pos] = new_v20;
	dest[96 + pos] = new_v21;
	dest[112 + pos] = new_v22;
	dest[128 + pos] = new_v23;
	dest[144 + pos] = new_v24;
	dest[160 + pos] = new_v25;
	dest[176 + pos] = new_v26;
	dest[192 + pos] = new_v27;
	dest[208 + pos] = new_v28;
	dest[224 + pos] = new_v29;
	dest[240 + pos] = new_v30;
	dest[256 + pos] = new_v31;
	
	// insert V[49-63] (== new_v[30-16]) into other v:
	dest[272 + pos] = new_v30;
	dest[288 + pos] = new_v29;
	dest[304 + pos] = new_v28;
	dest[320 + pos] = new_v27;
	dest[336 + pos] = new_v26;
	dest[352 + pos] = new_v25;
	dest[368 + pos] = new_v24;
	dest[384 + pos] = new_v23;
	dest[400 + pos] = new_v22;
	dest[416 + pos] = new_v21;
	dest[432 + pos] = new_v20;
	dest[448 + pos] = new_v19;
	dest[464 + pos] = new_v18;
	dest[480 + pos] = new_v17;
	dest[496 + pos] = new_v16; 			
/*
	}
	else
	{
		v1[0 + actual_write_pos] = -new_v0;
		// insert V[33-48] (== new_v[16-31]) into other v:
		v1[16 + actual_write_pos] = new_v16;
		v1[32 + actual_write_pos] = new_v17;
		v1[48 + actual_write_pos] = new_v18;
		v1[64 + actual_write_pos] = new_v19;
		v1[80 + actual_write_pos] = new_v20;
		v1[96 + actual_write_pos] = new_v21;
		v1[112 + actual_write_pos] = new_v22;
		v1[128 + actual_write_pos] = new_v23;
		v1[144 + actual_write_pos] = new_v24;
		v1[160 + actual_write_pos] = new_v25;
		v1[176 + actual_write_pos] = new_v26;
		v1[192 + actual_write_pos] = new_v27;
		v1[208 + actual_write_pos] = new_v28;
		v1[224 + actual_write_pos] = new_v29;
		v1[240 + actual_write_pos] = new_v30;
		v1[256 + actual_write_pos] = new_v31;

		// insert V[49-63] (== new_v[30-16]) into other v:
		v1[272 + actual_write_pos] = new_v30;
		v1[288 + actual_write_pos] = new_v29;
		v1[304 + actual_write_pos] = new_v28;
		v1[320 + actual_write_pos] = new_v27;
		v1[336 + actual_write_pos] = new_v26;
		v1[352 + actual_write_pos] = new_v25;
		v1[368 + actual_write_pos] = new_v24;
		v1[384 + actual_write_pos] = new_v23;
		v1[400 + actual_write_pos] = new_v22;
		v1[416 + actual_write_pos] = new_v21;
		v1[432 + actual_write_pos] = new_v20;
		v1[448 + actual_write_pos] = new_v19;
		v1[464 + actual_write_pos] = new_v18;
		v1[480 + actual_write_pos] = new_v17;
		v1[496 + actual_write_pos] = new_v16;	
	}
*/	
  }
	
  /**
   * Compute new values via a fast cosine transform.
   */
  private void compute_new_v_old()
  {
	// p is fully initialized from x1
	 //float[] p = _p;
	 // pp is fully initialized from p
	 //float[] pp = _pp; 
	  
	 //float[] new_v = _new_v;
	  
  	float[] new_v = new float[32]; // new V[0-15] and V[33-48] of Figure 3-A.2 in ISO DIS 11172-3
	float[] p = new float[16];
	float[] pp = new float[16];
	  
	  
	 for (int i=31; i>=0; i--)
	 {
		 new_v[i] = 0.0f;
	 }
	 
//	float[] new_v = new float[32]; // new V[0-15] and V[33-48] of Figure 3-A.2 in ISO DIS 11172-3
//	float[] p = new float[16];
//	float[] pp = new float[16];

    float[] x1 = samples;
	
	p[0] = x1[0] + x1[31];
	p[1] = x1[1] + x1[30];
	p[2] = x1[2] + x1[29];
	p[3] = x1[3] + x1[28];
	p[4] = x1[4] + x1[27];
	p[5] = x1[5] + x1[26];
	p[6] = x1[6] + x1[25];
	p[7] = x1[7] + x1[24];
	p[8] = x1[8] + x1[23];
	p[9] = x1[9] + x1[22];
	p[10] = x1[10] + x1[21];
	p[11] = x1[11] + x1[20];
	p[12] = x1[12] + x1[19];
	p[13] = x1[13] + x1[18];
	p[14] = x1[14] + x1[17];
	p[15] = x1[15] + x1[16];
	
	pp[0] = p[0] + p[15];
	pp[1] = p[1] + p[14];
	pp[2] = p[2] + p[13];
	pp[3] = p[3] + p[12];
	pp[4] = p[4] + p[11];
	pp[5] = p[5] + p[10];
	pp[6] = p[6] + p[9];
	pp[7] = p[7] + p[8];
	pp[8] = (p[0] - p[15]) * cos1_32;
	pp[9] = (p[1] - p[14]) * cos3_32;
	pp[10] = (p[2] - p[13]) * cos5_32;
	pp[11] = (p[3] - p[12]) * cos7_32;
	pp[12] = (p[4] - p[11]) * cos9_32;
	pp[13] = (p[5] - p[10]) * cos11_32;
	pp[14] = (p[6] - p[9]) * cos13_32;
	pp[15] = (p[7] - p[8]) * cos15_32;

	p[0] = pp[0] + pp[7];
	p[1] = pp[1] + pp[6];
	p[2] = pp[2] + pp[5];
	p[3] = pp[3] + pp[4];
	p[4] = (pp[0] - pp[7]) * cos1_16;
	p[5] = (pp[1] - pp[6]) * cos3_16;
	p[6] = (pp[2] - pp[5]) * cos5_16;
	p[7] = (pp[3] - pp[4]) * cos7_16;
	p[8] = pp[8] + pp[15];
	p[9] = pp[9] + pp[14];
	p[10] = pp[10] + pp[13];
	p[11] = pp[11] + pp[12];
	p[12] = (pp[8] - pp[15]) * cos1_16;
	p[13] = (pp[9] - pp[14]) * cos3_16;
	p[14] = (pp[10] - pp[13]) * cos5_16;
	p[15] = (pp[11] - pp[12]) * cos7_16;
	

	pp[0] = p[0] + p[3];
	pp[1] = p[1] + p[2];
	pp[2] = (p[0] - p[3]) * cos1_8;
	pp[3] = (p[1] - p[2]) * cos3_8;
	pp[4] = p[4] + p[7];
	pp[5] = p[5] + p[6];
	pp[6] = (p[4] - p[7]) * cos1_8;
	pp[7] = (p[5] - p[6]) * cos3_8;
	pp[8] = p[8] + p[11];
	pp[9] = p[9] + p[10];
	pp[10] = (p[8] - p[11]) * cos1_8;
	pp[11] = (p[9] - p[10]) * cos3_8;
	pp[12] = p[12] + p[15];
	pp[13] = p[13] + p[14];
	pp[14] = (p[12] - p[15]) * cos1_8;
	pp[15] = (p[13] - p[14]) * cos3_8;

	p[0] = pp[0] + pp[1];
	p[1] = (pp[0] - pp[1]) * cos1_4;
	p[2] = pp[2] + pp[3];
	p[3] = (pp[2] - pp[3]) * cos1_4;
	p[4] = pp[4] + pp[5];
	p[5] = (pp[4] - pp[5]) * cos1_4;
	p[6] = pp[6] + pp[7];
	p[7] = (pp[6] - pp[7]) * cos1_4;
	p[8] = pp[8] + pp[9];
	p[9] = (pp[8] - pp[9]) * cos1_4;
	p[10] = pp[10] + pp[11];
	p[11] = (pp[10] - pp[11]) * cos1_4;
	p[12] = pp[12] + pp[13];
	p[13] = (pp[12] - pp[13]) * cos1_4;
	p[14] = pp[14] + pp[15];
	p[15] = (pp[14] - pp[15]) * cos1_4;

	// this is pretty insane coding
	float tmp1;
	new_v[36-17] = -(new_v[4] = (new_v[12] = p[7]) + p[5]) - p[6];
	new_v[44-17] = -p[6] - p[7] - p[4];
	new_v[6] = (new_v[10] = (new_v[14] = p[15]) + p[11]) + p[13];
	new_v[34-17] = -(new_v[2] = p[15] + p[13] + p[9]) - p[14];
	new_v[38-17] = (tmp1 = -p[14] - p[15] - p[10] - p[11]) - p[13];
	new_v[46-17] = -p[14] - p[15] - p[12] - p[8];
	new_v[42-17] = tmp1 - p[12];
	new_v[48-17] = -p[0];
	new_v[0] = p[1];
	new_v[40-17] = -(new_v[8] = p[3]) - p[2];
	
	p[0] = (x1[0] - x1[31]) * cos1_64;
	p[1] = (x1[1] - x1[30]) * cos3_64;
	p[2] = (x1[2] - x1[29]) * cos5_64;
	p[3] = (x1[3] - x1[28]) * cos7_64;
	p[4] = (x1[4] - x1[27]) * cos9_64;
	p[5] = (x1[5] - x1[26]) * cos11_64;
	p[6] = (x1[6] - x1[25]) * cos13_64;
	p[7] = (x1[7] - x1[24]) * cos15_64;
	p[8] = (x1[8] - x1[23]) * cos17_64;
	p[9] = (x1[9] - x1[22]) * cos19_64;
	p[10] = (x1[10] - x1[21]) * cos21_64;
	p[11] = (x1[11] - x1[20]) * cos23_64;
	p[12] = (x1[12] - x1[19]) * cos25_64;
	p[13] = (x1[13] - x1[18]) * cos27_64;
	p[14] = (x1[14] - x1[17]) * cos29_64;
	p[15] = (x1[15] - x1[16]) * cos31_64;

	
	pp[0] = p[0] + p[15];
	pp[1] = p[1] + p[14];
	pp[2] = p[2] + p[13];
	pp[3] = p[3] + p[12];
	pp[4] = p[4] + p[11];
	pp[5] = p[5] + p[10];
	pp[6] = p[6] + p[9];
	pp[7] = p[7] + p[8];
	pp[8] = (p[0] - p[15]) * cos1_32;
	pp[9] = (p[1] - p[14]) * cos3_32;
	pp[10] = (p[2] - p[13]) * cos5_32;
	pp[11] = (p[3] - p[12]) * cos7_32;
	pp[12] = (p[4] - p[11]) * cos9_32;
	pp[13] = (p[5] - p[10]) * cos11_32;
	pp[14] = (p[6] - p[9]) * cos13_32;
	pp[15] = (p[7] - p[8]) * cos15_32;
	

	p[0] = pp[0] + pp[7];
	p[1] = pp[1] + pp[6];
	p[2] = pp[2] + pp[5];
	p[3] = pp[3] + pp[4];
	p[4] = (pp[0] - pp[7]) * cos1_16;
	p[5] = (pp[1] - pp[6]) * cos3_16;
	p[6] = (pp[2] - pp[5]) * cos5_16;
	p[7] = (pp[3] - pp[4]) * cos7_16;
	p[8] = pp[8] + pp[15];
	p[9] = pp[9] + pp[14];
	p[10] = pp[10] + pp[13];
	p[11] = pp[11] + pp[12];
	p[12] = (pp[8] - pp[15]) * cos1_16;
	p[13] = (pp[9] - pp[14]) * cos3_16;
	p[14] = (pp[10] - pp[13]) * cos5_16;
	p[15] = (pp[11] - pp[12]) * cos7_16;


	pp[0] = p[0] + p[3];
	pp[1] = p[1] + p[2];
	pp[2] = (p[0] - p[3]) * cos1_8;
	pp[3] = (p[1] - p[2]) * cos3_8;
	pp[4] = p[4] + p[7];
	pp[5] = p[5] + p[6];
	pp[6] = (p[4] - p[7]) * cos1_8;
	pp[7] = (p[5] - p[6]) * cos3_8;
	pp[8] = p[8] + p[11];
	pp[9] = p[9] + p[10];
	pp[10] = (p[8] - p[11]) * cos1_8;
	pp[11] = (p[9] - p[10]) * cos3_8;
	pp[12] = p[12] + p[15];
	pp[13] = p[13] + p[14];
	pp[14] = (p[12] - p[15]) * cos1_8;
	pp[15] = (p[13] - p[14]) * cos3_8;

	
	p[0] = pp[0] + pp[1];
	p[1] = (pp[0] - pp[1]) * cos1_4;
	p[2] = pp[2] + pp[3];
	p[3] = (pp[2] - pp[3]) * cos1_4;
	p[4] = pp[4] + pp[5];
	p[5] = (pp[4] - pp[5]) * cos1_4;
	p[6] = pp[6] + pp[7];
	p[7] = (pp[6] - pp[7]) * cos1_4;
	p[8] = pp[8] + pp[9];
	p[9] = (pp[8] - pp[9]) * cos1_4;
	p[10] = pp[10] + pp[11];
	p[11] = (pp[10] - pp[11]) * cos1_4;
	p[12] = pp[12] + pp[13];
	p[13] = (pp[12] - pp[13]) * cos1_4;
	p[14] = pp[14] + pp[15];
	p[15] = (pp[14] - pp[15]) * cos1_4;
	

	// manually doing something that a compiler should handle sucks
	// coding like this is hard to read
	float tmp2;
	new_v[5] = (new_v[11] = (new_v[13] = (new_v[15] = p[15]) + p[7]) + p[11])
							+ p[5] + p[13];
	new_v[7] = (new_v[9] = p[15] + p[11] + p[3]) + p[13];
	new_v[33-17] = -(new_v[1] = (tmp1 = p[13] + p[15] + p[9]) + p[1]) - p[14];
	new_v[35-17] = -(new_v[3] = tmp1 + p[5] + p[7]) - p[6] - p[14];

	new_v[39-17] = (tmp1 = -p[10] - p[11] - p[14] - p[15])
									- p[13] - p[2] - p[3];
	new_v[37-17] = tmp1 - p[13] - p[5] - p[6] - p[7];
	new_v[41-17] = tmp1 - p[12] - p[2] - p[3];
	new_v[43-17] = tmp1 - p[12] - (tmp2 = p[4] + p[6] + p[7]);
	new_v[47-17] = (tmp1 = -p[8] - p[12] - p[14] - p[15]) - p[0];
	new_v[45-17] = tmp1 - tmp2;

	// insert V[0-15] (== new_v[0-15]) into actual v:
	x1 = new_v;
	// float[] x2 = actual_v + actual_write_pos;
	float[] dest = actual_v;
	
	dest[0 + actual_write_pos] = x1[0];
	dest[16 + actual_write_pos] = x1[1];
	dest[32 + actual_write_pos] = x1[2];
	dest[48 + actual_write_pos] = x1[3];
	dest[64 + actual_write_pos] = x1[4];
	dest[80 + actual_write_pos] = x1[5];
	dest[96 + actual_write_pos] = x1[6];
	dest[112 + actual_write_pos] = x1[7];
	dest[128 + actual_write_pos] = x1[8];
	dest[144 + actual_write_pos] = x1[9];
	dest[160 + actual_write_pos] = x1[10];
	dest[176 + actual_write_pos] = x1[11];
	dest[192 + actual_write_pos] = x1[12];
	dest[208 + actual_write_pos] = x1[13];
	dest[224 + actual_write_pos] = x1[14];
	dest[240 + actual_write_pos] = x1[15];

	// V[16] is always 0.0:
	dest[256 + actual_write_pos] = 0.0f;

	// insert V[17-31] (== -new_v[15-1]) into actual v:
	dest[272 + actual_write_pos] = -x1[15];
	dest[288 + actual_write_pos] = -x1[14];
	dest[304 + actual_write_pos] = -x1[13];
	dest[320 + actual_write_pos] = -x1[12];
	dest[336 + actual_write_pos] = -x1[11];
	dest[352 + actual_write_pos] = -x1[10];
	dest[368 + actual_write_pos] = -x1[9];
	dest[384 + actual_write_pos] = -x1[8];
	dest[400 + actual_write_pos] = -x1[7];
	dest[416 + actual_write_pos] = -x1[6];
	dest[432 + actual_write_pos] = -x1[5];
	dest[448 + actual_write_pos] = -x1[4];
	dest[464 + actual_write_pos] = -x1[3];
	dest[480 + actual_write_pos] = -x1[2];
	dest[496 + actual_write_pos] = -x1[1];

	// insert V[32] (== -new_v[0]) into other v:
	
  }

  /**
   * Compute PCM Samples.
   */
  
  private float[] _tmpOut = new float[32];
  
  
  private void compute_pcm_samples0(Obuffer buffer)
  {
	final float[] vp = actual_v;	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
		float pcm_sample;
		final float[] dp = d16[i];
		pcm_sample = (float)(((vp[0 + dvp] * dp[0]) +
			(vp[15 + dvp] * dp[1]) +
			(vp[14 + dvp] * dp[2]) +
			(vp[13 + dvp] * dp[3]) +
			(vp[12 + dvp] * dp[4]) +
			(vp[11 + dvp] * dp[5]) +
			(vp[10 + dvp] * dp[6]) +
			(vp[9 + dvp] * dp[7]) +
			(vp[8 + dvp] * dp[8]) +
			(vp[7 + dvp] * dp[9]) +
			(vp[6 + dvp] * dp[10]) +
			(vp[5 + dvp] * dp[11]) +
			(vp[4 + dvp] * dp[12]) +
			(vp[3 + dvp] * dp[13]) +
			(vp[2 + dvp] * dp[14]) +
			(vp[1 + dvp] * dp[15])
			) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
	} // for
  }
  
  private void compute_pcm_samples1(Obuffer buffer)
  {
	final float[] vp = actual_v;	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[1 + dvp] * dp[0]) +
					(vp[0 + dvp] * dp[1]) +
					(vp[15 + dvp] * dp[2]) +
					(vp[14 + dvp] * dp[3]) +
					(vp[13 + dvp] * dp[4]) +
					(vp[12 + dvp] * dp[5]) +
					(vp[11 + dvp] * dp[6]) +
					(vp[10 + dvp] * dp[7]) +
					(vp[9 + dvp] * dp[8]) +
					(vp[8 + dvp] * dp[9]) +
					(vp[7 + dvp] * dp[10]) +
					(vp[6 + dvp] * dp[11]) +
					(vp[5 + dvp] * dp[12]) +
					(vp[4 + dvp] * dp[13]) +
					(vp[3 + dvp] * dp[14]) +
					(vp[2 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
         } // for
  }
    private void compute_pcm_samples2(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[2 + dvp] * dp[0]) +
					(vp[1 + dvp] * dp[1]) +
					(vp[0 + dvp] * dp[2]) +
					(vp[15 + dvp] * dp[3]) +
					(vp[14 + dvp] * dp[4]) +
					(vp[13 + dvp] * dp[5]) +
					(vp[12 + dvp] * dp[6]) +
					(vp[11 + dvp] * dp[7]) +
					(vp[10 + dvp] * dp[8]) +
					(vp[9 + dvp] * dp[9]) +
					(vp[8 + dvp] * dp[10]) +
					(vp[7 + dvp] * dp[11]) +
					(vp[6 + dvp] * dp[12]) +
					(vp[5 + dvp] * dp[13]) +
					(vp[4 + dvp] * dp[14]) +
					(vp[3 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
	}
	
	  private void compute_pcm_samples3(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	int idx = 0;
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[3 + dvp] * dp[0]) +
					(vp[2 + dvp] * dp[1]) +
					(vp[1 + dvp] * dp[2]) +
					(vp[0 + dvp] * dp[3]) +
					(vp[15 + dvp] * dp[4]) +
					(vp[14 + dvp] * dp[5]) +
					(vp[13 + dvp] * dp[6]) +
					(vp[12 + dvp] * dp[7]) +
					(vp[11 + dvp] * dp[8]) +
					(vp[10 + dvp] * dp[9]) +
					(vp[9 + dvp] * dp[10]) +
					(vp[8 + dvp] * dp[11]) +
					(vp[7 + dvp] * dp[12]) +
					(vp[6 + dvp] * dp[13]) +
					(vp[5 + dvp] * dp[14]) +
					(vp[4 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
	  }
			
  private void compute_pcm_samples4(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[4 + dvp] * dp[0]) +
					(vp[3 + dvp] * dp[1]) +
					(vp[2 + dvp] * dp[2]) +
					(vp[1 + dvp] * dp[3]) +
					(vp[0 + dvp] * dp[4]) +
					(vp[15 + dvp] * dp[5]) +
					(vp[14 + dvp] * dp[6]) +
					(vp[13 + dvp] * dp[7]) +
					(vp[12 + dvp] * dp[8]) +
					(vp[11 + dvp] * dp[9]) +
					(vp[10 + dvp] * dp[10]) +
					(vp[9 + dvp] * dp[11]) +
					(vp[8 + dvp] * dp[12]) +
					(vp[7 + dvp] * dp[13]) +
					(vp[6 + dvp] * dp[14]) +
					(vp[5 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  
  private void compute_pcm_samples5(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[5 + dvp] * dp[0]) +
					(vp[4 + dvp] * dp[1]) +
					(vp[3 + dvp] * dp[2]) +
					(vp[2 + dvp] * dp[3]) +
					(vp[1 + dvp] * dp[4]) +
					(vp[0 + dvp] * dp[5]) +
					(vp[15 + dvp] * dp[6]) +
					(vp[14 + dvp] * dp[7]) +
					(vp[13 + dvp] * dp[8]) +
					(vp[12 + dvp] * dp[9]) +
					(vp[11 + dvp] * dp[10]) +
					(vp[10 + dvp] * dp[11]) +
					(vp[9 + dvp] * dp[12]) +
					(vp[8 + dvp] * dp[13]) +
					(vp[7 + dvp] * dp[14]) +
					(vp[6 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  
  private void compute_pcm_samples6(Obuffer buffer)
  {
	final float[] vp = actual_v;	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[6 + dvp] * dp[0]) +
					(vp[5 + dvp] * dp[1]) +
					(vp[4 + dvp] * dp[2]) +
					(vp[3 + dvp] * dp[3]) +
					(vp[2 + dvp] * dp[4]) +
					(vp[1 + dvp] * dp[5]) +
					(vp[0 + dvp] * dp[6]) +
					(vp[15 + dvp] * dp[7]) +
					(vp[14 + dvp] * dp[8]) +
					(vp[13 + dvp] * dp[9]) +
					(vp[12 + dvp] * dp[10]) +
					(vp[11 + dvp] * dp[11]) +
					(vp[10 + dvp] * dp[12]) +
					(vp[9 + dvp] * dp[13]) +
					(vp[8 + dvp] * dp[14]) +
					(vp[7 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  
    private void compute_pcm_samples7(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[7 + dvp] * dp[0]) +
					(vp[6 + dvp] * dp[1]) +
					(vp[5 + dvp] * dp[2]) +
					(vp[4 + dvp] * dp[3]) +
					(vp[3 + dvp] * dp[4]) +
					(vp[2 + dvp] * dp[5]) +
					(vp[1 + dvp] * dp[6]) +
					(vp[0 + dvp] * dp[7]) +
					(vp[15 + dvp] * dp[8]) +
					(vp[14 + dvp] * dp[9]) +
					(vp[13 + dvp] * dp[10]) +
					(vp[12 + dvp] * dp[11]) +
					(vp[11 + dvp] * dp[12]) +
					(vp[10 + dvp] * dp[13]) +
					(vp[9 + dvp] * dp[14]) +
					(vp[8 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
	}
  private void compute_pcm_samples8(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[8 + dvp] * dp[0]) +
					(vp[7 + dvp] * dp[1]) +
					(vp[6 + dvp] * dp[2]) +
					(vp[5 + dvp] * dp[3]) +
					(vp[4 + dvp] * dp[4]) +
					(vp[3 + dvp] * dp[5]) +
					(vp[2 + dvp] * dp[6]) +
					(vp[1 + dvp] * dp[7]) +
					(vp[0 + dvp] * dp[8]) +
					(vp[15 + dvp] * dp[9]) +
					(vp[14 + dvp] * dp[10]) +
					(vp[13 + dvp] * dp[11]) +
					(vp[12 + dvp] * dp[12]) +
					(vp[11 + dvp] * dp[13]) +
					(vp[10 + dvp] * dp[14]) +
					(vp[9 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  
  private void compute_pcm_samples9(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[9 + dvp] * dp[0]) +
					(vp[8 + dvp] * dp[1]) +
					(vp[7 + dvp] * dp[2]) +
					(vp[6 + dvp] * dp[3]) +
					(vp[5 + dvp] * dp[4]) +
					(vp[4 + dvp] * dp[5]) +
					(vp[3 + dvp] * dp[6]) +
					(vp[2 + dvp] * dp[7]) +
					(vp[1 + dvp] * dp[8]) +
					(vp[0 + dvp] * dp[9]) +
					(vp[15 + dvp] * dp[10]) +
					(vp[14 + dvp] * dp[11]) +
					(vp[13 + dvp] * dp[12]) +
					(vp[12 + dvp] * dp[13]) +
					(vp[11 + dvp] * dp[14]) +
					(vp[10 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  
  private void compute_pcm_samples10(Obuffer buffer)
  {
	final float[] vp = actual_v;	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[10 + dvp] * dp[0]) +
					(vp[9 + dvp] * dp[1]) +
					(vp[8 + dvp] * dp[2]) +
					(vp[7 + dvp] * dp[3]) +
					(vp[6 + dvp] * dp[4]) +
					(vp[5 + dvp] * dp[5]) +
					(vp[4 + dvp] * dp[6]) +
					(vp[3 + dvp] * dp[7]) +
					(vp[2 + dvp] * dp[8]) +
					(vp[1 + dvp] * dp[9]) +
					(vp[0 + dvp] * dp[10]) +
					(vp[15 + dvp] * dp[11]) +
					(vp[14 + dvp] * dp[12]) +
					(vp[13 + dvp] * dp[13]) +
					(vp[12 + dvp] * dp[14]) +
					(vp[11 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  private void compute_pcm_samples11(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[11 + dvp] * dp[0]) +
					(vp[10 + dvp] * dp[1]) +
					(vp[9 + dvp] * dp[2]) +
					(vp[8 + dvp] * dp[3]) +
					(vp[7 + dvp] * dp[4]) +
					(vp[6 + dvp] * dp[5]) +
					(vp[5 + dvp] * dp[6]) +
					(vp[4 + dvp] * dp[7]) +
					(vp[3 + dvp] * dp[8]) +
					(vp[2 + dvp] * dp[9]) +
					(vp[1 + dvp] * dp[10]) +
					(vp[0 + dvp] * dp[11]) +
					(vp[15 + dvp] * dp[12]) +
					(vp[14 + dvp] * dp[13]) +
					(vp[13 + dvp] * dp[14]) +
					(vp[12 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
    private void compute_pcm_samples12(Obuffer buffer)
  {
	final float[] vp = actual_v;	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
			    final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[12 + dvp] * dp[0]) +
					(vp[11 + dvp] * dp[1]) +
					(vp[10 + dvp] * dp[2]) +
					(vp[9 + dvp] * dp[3]) +
					(vp[8 + dvp] * dp[4]) +
					(vp[7 + dvp] * dp[5]) +
					(vp[6 + dvp] * dp[6]) +
					(vp[5 + dvp] * dp[7]) +
					(vp[4 + dvp] * dp[8]) +
					(vp[3 + dvp] * dp[9]) +
					(vp[2 + dvp] * dp[10]) +
					(vp[1 + dvp] * dp[11]) +
					(vp[0 + dvp] * dp[12]) +
					(vp[15 + dvp] * dp[13]) +
					(vp[14 + dvp] * dp[14]) +
					(vp[13 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
	}
  private void compute_pcm_samples13(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[13 + dvp] * dp[0]) +
					(vp[12 + dvp] * dp[1]) +
					(vp[11 + dvp] * dp[2]) +
					(vp[10 + dvp] * dp[3]) +
					(vp[9 + dvp] * dp[4]) +
					(vp[8 + dvp] * dp[5]) +
					(vp[7 + dvp] * dp[6]) +
					(vp[6 + dvp] * dp[7]) +
					(vp[5 + dvp] * dp[8]) +
					(vp[4 + dvp] * dp[9]) +
					(vp[3 + dvp] * dp[10]) +
					(vp[2 + dvp] * dp[11]) +
					(vp[1 + dvp] * dp[12]) +
					(vp[0 + dvp] * dp[13]) +
					(vp[15 + dvp] * dp[14]) +
					(vp[14 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  private void compute_pcm_samples14(Obuffer buffer)
  {
	final float[] vp = actual_v;
	
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				final float[] dp = d16[i];
				float pcm_sample;

				pcm_sample = (float)(((vp[14 + dvp] * dp[0]) +
					(vp[13 + dvp] * dp[1]) +
					(vp[12 + dvp] * dp[2]) +
					(vp[11 + dvp] * dp[3]) +
					(vp[10 + dvp] * dp[4]) +
					(vp[9 + dvp] * dp[5]) +
					(vp[8 + dvp] * dp[6]) +
					(vp[7 + dvp] * dp[7]) +
					(vp[6 + dvp] * dp[8]) +
					(vp[5 + dvp] * dp[9]) +
					(vp[4 + dvp] * dp[10]) +
					(vp[3 + dvp] * dp[11]) +
					(vp[2 + dvp] * dp[12]) +
					(vp[1 + dvp] * dp[13]) +
					(vp[0 + dvp] * dp[14]) +
					(vp[15 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;
			
			dvp += 16;
			} // for
  }
  private void compute_pcm_samples15(Obuffer buffer)
  {
	final float[] vp = actual_v;
		
	//int inc = v_inc;
	final float[] tmpOut = _tmpOut;
	 int dvp =0;
	
			// fat chance of having this loop unroll
			for( int i=0; i<32; i++)
			{
				float pcm_sample;
				final float dp[] = d16[i];
				pcm_sample = (float)(((vp[15 + dvp] * dp[0]) +
					(vp[14 + dvp] * dp[1]) +
					(vp[13 + dvp] * dp[2]) +
					(vp[12 + dvp] * dp[3]) +
					(vp[11 + dvp] * dp[4]) +
					(vp[10 + dvp] * dp[5]) +
					(vp[9 + dvp] * dp[6]) +
					(vp[8 + dvp] * dp[7]) +
					(vp[7 + dvp] * dp[8]) +
					(vp[6 + dvp] * dp[9]) +
					(vp[5 + dvp] * dp[10]) +
					(vp[4 + dvp] * dp[11]) +
					(vp[3 + dvp] * dp[12]) +
					(vp[2 + dvp] * dp[13]) +
					(vp[1 + dvp] * dp[14]) +
					(vp[0 + dvp] * dp[15])
					) * scalefactor);

            tmpOut[i] = pcm_sample;			
			dvp += 16;
			} // for
		}
	 	 	 	 
private void compute_pcm_samples(Obuffer buffer)
{
	
	switch (actual_write_pos)
	{
	case 0: 
		compute_pcm_samples0(buffer);
		break;
	case 1: 
		compute_pcm_samples1(buffer);
		break;
	case 2: 
		compute_pcm_samples2(buffer);
		break;
	case 3: 
		compute_pcm_samples3(buffer);
		break;
	case 4: 
		compute_pcm_samples4(buffer);
		break;
	case 5: 
		compute_pcm_samples5(buffer);
		break;
	case 6: 
		compute_pcm_samples6(buffer);
		break;
	case 7: 
		compute_pcm_samples7(buffer);
		break;
	case 8: 
		compute_pcm_samples8(buffer);
		break;
	case 9: 
		compute_pcm_samples9(buffer);
		break;
	case 10: 
		compute_pcm_samples10(buffer);
		break;
	case 11: 
		compute_pcm_samples11(buffer);
		break;
	case 12: 
		compute_pcm_samples12(buffer);
		break;
	case 13: 
		compute_pcm_samples13(buffer);
		break;
	case 14: 
		compute_pcm_samples14(buffer);
		break;
	case 15: 
		compute_pcm_samples15(buffer);
		break;
	}
		
	if (buffer!=null)
	{		
		buffer.appendSamples(channel, _tmpOut);
	}
	 
/*
	 // MDM: I was considering putting in quality control for
	 // low-spec CPUs, but the performance gain (about 10-15%) 
	 // did not justify the considerable drop in audio quality.
		switch (inc)
		{
		case 16:		 
		    buffer.appendSamples(channel, tmpOut);
		    break;
		case 32:
			for (int i=0; i<16; i++)
			{
				buffer.append(channel, (short)tmpOut[i]);
				buffer.append(channel, (short)tmpOut[i]); 
			}
			break;			
		case 64:
			for (int i=0; i<8; i++)
			{
				buffer.append(channel, (short)tmpOut[i]);
				buffer.append(channel, (short)tmpOut[i]);
				buffer.append(channel, (short)tmpOut[i]);
				buffer.append(channel, (short)tmpOut[i]); 
			}
			break;			
	
		}
*/	 
  }

  /**
   * Calculate 32 PCM samples and put the into the Obuffer-object.
   */
	
  public void calculate_pcm_samples(Obuffer buffer)
  {
	compute_new_v();	
	compute_pcm_samples(buffer);
    
	actual_write_pos = (actual_write_pos + 1) & 0xf;
	actual_v = (actual_v == v1) ? v2 : v1;

	// initialize samples[]:	
    //for (register float *floatp = samples + 32; floatp > samples; )
	// *--floatp = 0.0f;  
	
	// MDM: this may not be necessary. The Layer III decoder always
	// outputs 32 subband samples, but I haven't checked layer I & II.
	for (int p=0;p<32;p++) 
		samples[p] = 0.0f;
  }
  
  
  private static final double MY_PI = 3.14159265358979323846;
  private static final float cos1_64  =(float) (1.0 / (2.0 * Math.cos(MY_PI        / 64.0)));
  private static final float cos3_64  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 3.0  / 64.0)));
  private static final float cos5_64  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 5.0  / 64.0)));
  private static final float cos7_64  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 7.0  / 64.0)));
  private static final float cos9_64  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 9.0  / 64.0)));
  private static final float cos11_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 11.0 / 64.0)));
  private static final float cos13_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 13.0 / 64.0)));
  private static final float cos15_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 15.0 / 64.0)));
  private static final float cos17_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 17.0 / 64.0)));
  private static final float cos19_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 19.0 / 64.0)));
  private static final float cos21_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 21.0 / 64.0)));
  private static final float cos23_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 23.0 / 64.0)));
  private static final float cos25_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 25.0 / 64.0)));
  private static final float cos27_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 27.0 / 64.0)));
  private static final float cos29_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 29.0 / 64.0)));
  private static final float cos31_64 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 31.0 / 64.0)));
  private static final float cos1_32  =(float) (1.0 / (2.0 * Math.cos(MY_PI        / 32.0)));
  private static final float cos3_32  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 3.0  / 32.0)));
  private static final float cos5_32  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 5.0  / 32.0)));
  private static final float cos7_32  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 7.0  / 32.0)));
  private static final float cos9_32  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 9.0  / 32.0)));
  private static final float cos11_32 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 11.0 / 32.0)));
  private static final float cos13_32 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 13.0 / 32.0)));
  private static final float cos15_32 =(float) (1.0 / (2.0 * Math.cos(MY_PI * 15.0 / 32.0)));
  private static final float cos1_16  =(float) (1.0 / (2.0 * Math.cos(MY_PI        / 16.0)));
  private static final float cos3_16  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 3.0  / 16.0)));
  private static final float cos5_16  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 5.0  / 16.0)));
  private static final float cos7_16  =(float) (1.0 / (2.0 * Math.cos(MY_PI * 7.0  / 16.0)));
  private static final float cos1_8   =(float) (1.0 / (2.0 * Math.cos(MY_PI        / 8.0)));
  private static final float cos3_8   =(float) (1.0 / (2.0 * Math.cos(MY_PI * 3.0  / 8.0)));
  private static final float cos1_4   =(float) (1.0 / (2.0 * Math.cos(MY_PI / 4.0)));
  
  // Note: These values are not in the same order
  // as in Annex 3-B.3 of the ISO/IEC DIS 11172-3 
  // private float d[] = {0.000000000, -4.000442505};
  
  private static float d[] = null;
  
  /** 
   * d[] split into subarrays of length 16. This provides for
   * more faster access by allowing a block of 16 to be addressed
   * with constant offset. 
   **/
  private static float d16[][] = null;	
  
  /**
   * Loads the data for the d[] from the resource SFd.ser. 
   * @return the loaded values for d[].
   */
	static private float[] load_d()
	{
			String s = "0,-0.000442504999227821826934814453125,0.0032501220703125,-0.0070037841796875,0.0310821533203125,-0.0786285400390625,0.100311279296875,-0.5720367431640625,1.144989013671875,0.5720367431640625,0.100311279296875,0.0786285400390625,0.0310821533203125,0.0070037841796875,0.0032501220703125,0.000442504999227821826934814453125,-0.0000152590000652708113193511962890625,-0.0004730219952762126922607421875,0.003326416015625,-0.007919312454760074615478515625,0.030517578125,-0.0841827392578125,0.0909271240234375,-0.6002197265625,1.144287109375,0.5438232421875,0.108856201171875,0.07305908203125,0.0314788818359375,0.0061187739484012126922607421875,0.00317382789216935634613037109375,0.00039672901039011776447296142578125,-0.0000152590000652708113193511962890625,-0.0005340580246411263942718505859375,0.00338745093904435634613037109375,-0.0088653564453125,0.02978515625,-0.0897064208984375,0.0806884765625,-0.6282958984375,1.1422119140625,0.5156097412109375,0.1165771484375,0.0675201416015625,0.03173828125,0.0052947998046875,0.0030822749249637126922607421875,0.0003662109957076609134674072265625,-0.0000152590000652708113193511962890625,-0.000579833984375,0.0034332280047237873077392578125,-0.0098419189453125,0.0288848876953125,-0.0951690673828125,0.0695953369140625,-0.656219482421875,1.138763427734375,0.4874725341796875,0.12347412109375,0.0619964599609375,0.0318450927734375,0.004486083984375,0.00299072288908064365386962890625,0.00032043500686995685100555419921875,-0.0000152590000652708113193511962890625,-0.000625610002316534519195556640625,0.00346374488435685634613037109375,-0.0108489990234375,0.027801513671875,-0.1005401611328125,0.0576171875,-0.6839141845703125,1.1339263916015625,0.45947265625,0.12957763671875,0.0565338134765625,0.0318145751953125,0.0037231449969112873077392578125,0.002899169921875,0.0002899169921875,-0.0000152590000652708113193511962890625,-0.0006866459734737873077392578125,0.00347900390625,-0.0118865966796875,0.0265350341796875,-0.1058197021484375,0.0447845458984375,-0.7113189697265625,1.12774658203125,0.4316558837890625,0.1348876953125,0.0511322021484375,0.0316619873046875,0.0030059809796512126922607421875,0.0027923579327762126922607421875,0.0002593990066088736057281494140625,-0.0000152590000652708113193511962890625,-0.000747681013308465480804443359375,0.00347900390625,-0.012939453125,0.02508544921875,-0.1109466552734375,0.0310821533203125,-0.738372802734375,1.1202239990234375,0.404083251953125,0.1394500732421875,0.04583740234375,0.0313873291015625,0.00233459495939314365386962890625,0.00268554710783064365386962890625,0.00024414100334979593753814697265625,-0.000030518000130541622638702392578125,-0.0008087159949354827404022216796875,0.00346374488435685634613037109375,-0.0140228271484375,0.0234222412109375,-0.1159210205078125,0.016510009765625,-0.7650299072265625,1.1113739013671875,0.376800537109375,0.1432647705078125,0.0406341552734375,0.031005859375,0.0016937260515987873077392578125,0.0025787348859012126922607421875,0.000213623003219254314899444580078125,-0.000030518000130541622638702392578125,-0.00088500999845564365386962890625,0.00341796898283064365386962890625,-0.0151214599609375,0.021575927734375,-0.120697021484375,0.00106811500154435634613037109375,-0.7912139892578125,1.1012115478515625,0.3498687744140625,0.1463623046875,0.035552978515625,0.0305328369140625,0.00109863304533064365386962890625,0.0024566650390625,0.00019836399587802588939666748046875,-0.000030518000130541622638702392578125,-0.0009613040019758045673370361328125,0.0033721919171512126922607421875,-0.0162353515625,0.01953125,-0.1252593994140625,-0.015228270553052425384521484375,-0.816864013671875,1.08978271484375,0.3233184814453125,0.148773193359375,0.030609130859375,0.029937744140625,0.0005493159987963736057281494140625,0.0023498539812862873077392578125,0.00016784699982963502407073974609375,-0.000030518000130541622638702392578125,-0.001037598005495965480804443359375,0.0032806401140987873077392578125,-0.0173492431640625,0.0172576904296875,-0.1295623779296875,-0.032379150390625,-0.841949462890625,1.077117919921875,0.297210693359375,0.1504974365234375,0.02581787109375,0.0292816162109375,0.000030518000130541622638702392578125,0.0022430419921875,0.000152588007040321826934814453125,-0.00004577599975164048373699188232421875,-0.001113891950808465480804443359375,0.00317382789216935634613037109375,-0.018463134765625,0.014801025390625,-0.1335906982421875,-0.05035400390625,-0.866363525390625,1.0632171630859375,0.2715911865234375,0.1515960693359375,0.02117919921875,0.028533935546875,-0.000442504999227821826934814453125,0.00212097191251814365386962890625,0.000137328999699093401432037353515625,-0.00004577599975164048373699188232421875,-0.001205443986691534519195556640625,0.00305175804533064365386962890625,-0.0195770263671875,0.012115479446947574615478515625,-0.137298583984375,-0.0691680908203125,-0.8900909423828125,1.04815673828125,0.2465057373046875,0.152069091796875,0.0167083740234375,0.0277252197265625,-0.0008697509765625,0.00201415992341935634613037109375,0.0001220699996338225901126861572265625,-0.00006103499981691129505634307861328125,-0.001296996953897178173065185546875,0.00288391089998185634613037109375,-0.02069091796875,0.0092315673828125,-0.1406707763671875,-0.088775634765625,-0.913055419921875,1.0319366455078125,0.22198486328125,0.1519622802734375,0.012420654296875,0.0268402099609375,-0.0012664790265262126922607421875,0.001907348982058465480804443359375,0.0001068119963747449219226837158203125,-0.00006103499981691129505634307861328125,-0.00138855003751814365386962890625,0.00270080589689314365386962890625,-0.02178955078125,0.006134033203125,-0.1436767578125,-0.109161376953125,-0.9351959228515625,1.014617919921875,0.19805908203125,0.15130615234375,0.0083160400390625,0.025909423828125,-0.001617431989870965480804443359375,0.001785277971066534519195556640625,0.0001068119963747449219226837158203125,-0.0000762940035201609134674072265625,-0.0014801030047237873077392578125,0.0024871830828487873077392578125,-0.022857666015625,0.0028228759765625,-0.1462554931640625,-0.13031005859375,-0.95648193359375,0.996246337890625,0.1747894287109375,0.150115966796875,0.0043945307843387126922607421875,0.024932861328125,-0.00193786597810685634613037109375,0.0016937260515987873077392578125,0.0000915530035854317247867584228515625,-0.0000762940035201609134674072265625,-0.001586913946084678173065185546875,0.00222778297029435634613037109375,-0.0239105224609375,-0.0006866459734737873077392578125,-0.1484222412109375,-0.1522064208984375,-0.9768524169921875,0.9768524169921875,0.1522064208984375,0.1484222412109375,0.0006866459734737873077392578125,0.0239105224609375,-0.00222778297029435634613037109375,0.001586913946084678173065185546875,0.0000762940035201609134674072265625,-0.0000915530035854317247867584228515625,-0.0016937260515987873077392578125,0.00193786597810685634613037109375,-0.024932861328125,-0.0043945307843387126922607421875,-0.150115966796875,-0.1747894287109375,-0.996246337890625,0.95648193359375,0.13031005859375,0.1462554931640625,-0.0028228759765625,0.022857666015625,-0.0024871830828487873077392578125,0.0014801030047237873077392578125,0.0000762940035201609134674072265625,-0.0001068119963747449219226837158203125,-0.001785277971066534519195556640625,0.001617431989870965480804443359375,-0.025909423828125,-0.0083160400390625,-0.15130615234375,-0.19805908203125,-1.014617919921875,0.9351959228515625,0.109161376953125,0.1436767578125,-0.006134033203125,0.02178955078125,-0.00270080589689314365386962890625,0.00138855003751814365386962890625,0.00006103499981691129505634307861328125,-0.0001068119963747449219226837158203125,-0.001907348982058465480804443359375,0.0012664790265262126922607421875,-0.0268402099609375,-0.012420654296875,-0.1519622802734375,-0.22198486328125,-1.0319366455078125,0.913055419921875,0.088775634765625,0.1406707763671875,-0.0092315673828125,0.02069091796875,-0.00288391089998185634613037109375,0.001296996953897178173065185546875,0.00006103499981691129505634307861328125,-0.0001220699996338225901126861572265625,-0.00201415992341935634613037109375,0.0008697509765625,-0.0277252197265625,-0.0167083740234375,-0.152069091796875,-0.2465057373046875,-1.04815673828125,0.8900909423828125,0.0691680908203125,0.137298583984375,-0.012115479446947574615478515625,0.0195770263671875,-0.00305175804533064365386962890625,0.001205443986691534519195556640625,0.00004577599975164048373699188232421875,-0.000137328999699093401432037353515625,-0.00212097191251814365386962890625,0.000442504999227821826934814453125,-0.028533935546875,-0.02117919921875,-0.1515960693359375,-0.2715911865234375,-1.0632171630859375,0.866363525390625,0.05035400390625,0.1335906982421875,-0.014801025390625,0.018463134765625,-0.00317382789216935634613037109375,0.001113891950808465480804443359375,0.00004577599975164048373699188232421875,-0.000152588007040321826934814453125,-0.0022430419921875,-0.000030518000130541622638702392578125,-0.0292816162109375,-0.02581787109375,-0.1504974365234375,-0.297210693359375,-1.077117919921875,0.841949462890625,0.032379150390625,0.1295623779296875,-0.0172576904296875,0.0173492431640625,-0.0032806401140987873077392578125,0.001037598005495965480804443359375,0.000030518000130541622638702392578125,-0.00016784699982963502407073974609375,-0.0023498539812862873077392578125,-0.0005493159987963736057281494140625,-0.029937744140625,-0.030609130859375,-0.148773193359375,-0.3233184814453125,-1.08978271484375,0.816864013671875,0.015228270553052425384521484375,0.1252593994140625,-0.01953125,0.0162353515625,-0.0033721919171512126922607421875,0.0009613040019758045673370361328125,0.000030518000130541622638702392578125,-0.00019836399587802588939666748046875,-0.0024566650390625,-0.00109863304533064365386962890625,-0.0305328369140625,-0.035552978515625,-0.1463623046875,-0.3498687744140625,-1.1012115478515625,0.7912139892578125,-0.00106811500154435634613037109375,0.120697021484375,-0.021575927734375,0.0151214599609375,-0.00341796898283064365386962890625,0.00088500999845564365386962890625,0.000030518000130541622638702392578125,-0.000213623003219254314899444580078125,-0.0025787348859012126922607421875,-0.0016937260515987873077392578125,-0.031005859375,-0.0406341552734375,-0.1432647705078125,-0.376800537109375,-1.1113739013671875,0.7650299072265625,-0.016510009765625,0.1159210205078125,-0.0234222412109375,0.0140228271484375,-0.00346374488435685634613037109375,0.0008087159949354827404022216796875,0.000030518000130541622638702392578125,-0.00024414100334979593753814697265625,-0.00268554710783064365386962890625,-0.00233459495939314365386962890625,-0.0313873291015625,-0.04583740234375,-0.1394500732421875,-0.404083251953125,-1.1202239990234375,0.738372802734375,-0.0310821533203125,0.1109466552734375,-0.02508544921875,0.012939453125,-0.00347900390625,0.000747681013308465480804443359375,0.0000152590000652708113193511962890625,-0.0002593990066088736057281494140625,-0.0027923579327762126922607421875,-0.0030059809796512126922607421875,-0.0316619873046875,-0.0511322021484375,-0.1348876953125,-0.4316558837890625,-1.12774658203125,0.7113189697265625,-0.0447845458984375,0.1058197021484375,-0.0265350341796875,0.0118865966796875,-0.00347900390625,0.0006866459734737873077392578125,0.0000152590000652708113193511962890625,-0.0002899169921875,-0.002899169921875,-0.0037231449969112873077392578125,-0.0318145751953125,-0.0565338134765625,-0.12957763671875,-0.45947265625,-1.1339263916015625,0.6839141845703125,-0.0576171875,0.1005401611328125,-0.027801513671875,0.0108489990234375,-0.00346374488435685634613037109375,0.000625610002316534519195556640625,0.0000152590000652708113193511962890625,-0.00032043500686995685100555419921875,-0.00299072288908064365386962890625,-0.004486083984375,-0.0318450927734375,-0.0619964599609375,-0.12347412109375,-0.4874725341796875,-1.138763427734375,0.656219482421875,-0.0695953369140625,0.0951690673828125,-0.0288848876953125,0.0098419189453125,-0.0034332280047237873077392578125,0.000579833984375,0.0000152590000652708113193511962890625,-0.0003662109957076609134674072265625,-0.0030822749249637126922607421875,-0.0052947998046875,-0.03173828125,-0.0675201416015625,-0.1165771484375,-0.5156097412109375,-1.1422119140625,0.6282958984375,-0.0806884765625,0.0897064208984375,-0.02978515625,0.0088653564453125,-0.00338745093904435634613037109375,0.0005340580246411263942718505859375,0.0000152590000652708113193511962890625,-0.00039672901039011776447296142578125,-0.00317382789216935634613037109375,-0.0061187739484012126922607421875,-0.0314788818359375,-0.07305908203125,-0.108856201171875,-0.5438232421875,-1.144287109375,0.6002197265625,-0.0909271240234375,0.0841827392578125,-0.030517578125,0.007919312454760074615478515625,-0.003326416015625,0.0004730219952762126922607421875,0.0000152590000652708113193511962890625";
			String[] ss = s.split(",");
			
			float[] ff = new float[ss.length];
			for (int i = 0; i < ff.length; i++) {
			  ff[i] = new BigDecimal(ss[i]).floatValue();
      }
			
			return ff;
	}
	
	/**
	 * Converts a 1D array into a number of smaller arrays. This is used
	 * to achieve offset + constant indexing into an array. Each sub-array
	 * represents a block of values of the original array. 
	 * @param array			The array to split up into blocks.
	 * @param blockSize		The size of the blocks to split the array
	 *						into. This must be an exact divisor of
	 *						the length of the array, or some data
	 *						will be lost from the main array.
	 * 
	 * @return	An array of arrays in which each element in the returned
	 *			array will be of length <code>blockSize</code>.
	 */
	static private float[][] splitArray(final float[] array, final int blockSize)
	{
		int size = array.length / blockSize;
		float[][] split = new float[size][];
		for (int i=0; i<size; i++)
		{
			split[i] = subArray(array, i*blockSize, blockSize);
		}
		return split;
	}
	
	/**
	 * Returns a subarray of an existing array.
	 * 
	 * @param array	The array to retrieve a subarra from.
	 * @param offs	The offset in the array that corresponds to
	 *				the first index of the subarray.
	 * @param len	The number of indeces in the subarray.
	 * @return The subarray, which may be of length 0.
	 */
	static private float[] subArray(final float[] array, final int offs, int len)
	{
		if (offs+len > array.length)
		{
			len = array.length-offs;
		}
		
		if (len < 0)
			len = 0;
		
		float[] subarray = new float[len];
		for (int i=0; i<len; i++)
		{
			subarray[i] = array[offs+i];
		}
		
		return subarray;
	}
	
	// The original data for d[]. This data is loaded from a file
	// to reduce the overall package size and to improve performance. 
/*  
  static final float d_data[] = {
  	0.000000000f, -0.000442505f,  0.003250122f, -0.007003784f,
  	0.031082153f, -0.078628540f,  0.100311279f, -0.572036743f,
  	1.144989014f,  0.572036743f,  0.100311279f,  0.078628540f,
  	0.031082153f,  0.007003784f,  0.003250122f,  0.000442505f,
   -0.000015259f, -0.000473022f,  0.003326416f, -0.007919312f,
  	0.030517578f, -0.084182739f,  0.090927124f, -0.600219727f,
  	1.144287109f,  0.543823242f,  0.108856201f,  0.073059082f,
  	0.031478882f,  0.006118774f,  0.003173828f,  0.000396729f,
   -0.000015259f, -0.000534058f,  0.003387451f, -0.008865356f,
  	0.029785156f, -0.089706421f,  0.080688477f, -0.628295898f,
  	1.142211914f,  0.515609741f,  0.116577148f,  0.067520142f,
    0.031738281f,  0.005294800f,  0.003082275f,  0.000366211f,
   -0.000015259f, -0.000579834f,  0.003433228f, -0.009841919f,
    0.028884888f, -0.095169067f,  0.069595337f, -0.656219482f,
  	1.138763428f,  0.487472534f,  0.123474121f,  0.061996460f,
    0.031845093f,  0.004486084f,  0.002990723f,  0.000320435f,
   -0.000015259f, -0.000625610f,  0.003463745f, -0.010848999f,
    0.027801514f, -0.100540161f,  0.057617188f, -0.683914185f,
  	1.133926392f,  0.459472656f,  0.129577637f,  0.056533813f,
  	0.031814575f,  0.003723145f,  0.002899170f,  0.000289917f,
   -0.000015259f, -0.000686646f,  0.003479004f, -0.011886597f,
  	0.026535034f, -0.105819702f,  0.044784546f, -0.711318970f,
  	1.127746582f,  0.431655884f,  0.134887695f,  0.051132202f,
  	0.031661987f,  0.003005981f,  0.002792358f,  0.000259399f,
   -0.000015259f, -0.000747681f,  0.003479004f, -0.012939453f,
  	0.025085449f, -0.110946655f,  0.031082153f, -0.738372803f,
    1.120223999f,  0.404083252f,  0.139450073f,  0.045837402f,
    0.031387329f,  0.002334595f,  0.002685547f,  0.000244141f,
   -0.000030518f, -0.000808716f,  0.003463745f, -0.014022827f,
    0.023422241f, -0.115921021f,  0.016510010f, -0.765029907f,
  	1.111373901f,  0.376800537f,  0.143264771f,  0.040634155f,
    0.031005859f,  0.001693726f,  0.002578735f,  0.000213623f,
   -0.000030518f, -0.000885010f,  0.003417969f, -0.015121460f,
  	0.021575928f, -0.120697021f,  0.001068115f, -0.791213989f,
    1.101211548f,  0.349868774f,  0.146362305f,  0.035552979f,
  	0.030532837f,  0.001098633f,  0.002456665f,  0.000198364f,
   -0.000030518f, -0.000961304f,  0.003372192f, -0.016235352f,
    0.019531250f, -0.125259399f, -0.015228271f, -0.816864014f,
  	1.089782715f,  0.323318481f,  0.148773193f,  0.030609131f,
  	0.029937744f,  0.000549316f,  0.002349854f,  0.000167847f,
   -0.000030518f, -0.001037598f,  0.003280640f, -0.017349243f,
  	0.017257690f, -0.129562378f, -0.032379150f, -0.841949463f,
    1.077117920f,  0.297210693f,  0.150497437f,  0.025817871f,
    0.029281616f,  0.000030518f,  0.002243042f,  0.000152588f,
   -0.000045776f, -0.001113892f,  0.003173828f, -0.018463135f,
  	0.014801025f, -0.133590698f, -0.050354004f, -0.866363525f,
  	1.063217163f,  0.271591187f,  0.151596069f,  0.021179199f,
  	0.028533936f, -0.000442505f,  0.002120972f,  0.000137329f,
   -0.000045776f, -0.001205444f,  0.003051758f, -0.019577026f,
  	0.012115479f, -0.137298584f, -0.069168091f, -0.890090942f,
  	1.048156738f,  0.246505737f,  0.152069092f,  0.016708374f,
  	0.027725220f, -0.000869751f,  0.002014160f,  0.000122070f,
   -0.000061035f, -0.001296997f,  0.002883911f, -0.020690918f,
    0.009231567f, -0.140670776f, -0.088775635f, -0.913055420f,
  	1.031936646f,  0.221984863f,  0.151962280f,  0.012420654f,
    0.026840210f, -0.001266479f,  0.001907349f,  0.000106812f,
   -0.000061035f, -0.001388550f,  0.002700806f, -0.021789551f,
  	0.006134033f, -0.143676758f, -0.109161377f, -0.935195923f,
    1.014617920f,  0.198059082f,  0.151306152f,  0.008316040f,
  	0.025909424f, -0.001617432f,  0.001785278f,  0.000106812f,
   -0.000076294f, -0.001480103f,  0.002487183f, -0.022857666f,
  	0.002822876f, -0.146255493f, -0.130310059f, -0.956481934f,
  	0.996246338f,  0.174789429f,  0.150115967f,  0.004394531f,
    0.024932861f, -0.001937866f,  0.001693726f,  0.000091553f,
   -0.000076294f, -0.001586914f,  0.002227783f, -0.023910522f,
   -0.000686646f, -0.148422241f, -0.152206421f, -0.976852417f,
    0.976852417f,  0.152206421f,  0.148422241f,  0.000686646f,
  	0.023910522f, -0.002227783f,  0.001586914f,  0.000076294f,
   -0.000091553f, -0.001693726f,  0.001937866f, -0.024932861f,
   -0.004394531f, -0.150115967f, -0.174789429f, -0.996246338f,
    0.956481934f,  0.130310059f,  0.146255493f, -0.002822876f,
    0.022857666f, -0.002487183f,  0.001480103f,  0.000076294f,
   -0.000106812f, -0.001785278f,  0.001617432f, -0.025909424f,
   -0.008316040f, -0.151306152f, -0.198059082f, -1.014617920f,
    0.935195923f,  0.109161377f,  0.143676758f, -0.006134033f,
    0.021789551f, -0.002700806f,  0.001388550f,  0.000061035f,
   -0.000106812f, -0.001907349f,  0.001266479f, -0.026840210f,
   -0.012420654f, -0.151962280f, -0.221984863f, -1.031936646f,
  	0.913055420f,  0.088775635f,  0.140670776f, -0.009231567f,
  	0.020690918f, -0.002883911f,  0.001296997f,  0.000061035f,
   -0.000122070f, -0.002014160f,  0.000869751f, -0.027725220f,
   -0.016708374f, -0.152069092f, -0.246505737f, -1.048156738f,
    0.890090942f,  0.069168091f,  0.137298584f, -0.012115479f,
  	0.019577026f, -0.003051758f,  0.001205444f,  0.000045776f,
   -0.000137329f, -0.002120972f,  0.000442505f, -0.028533936f,
   -0.021179199f, -0.151596069f, -0.271591187f, -1.063217163f,
    0.866363525f,  0.050354004f,  0.133590698f, -0.014801025f,
    0.018463135f, -0.003173828f,  0.001113892f,  0.000045776f,
   -0.000152588f, -0.002243042f, -0.000030518f, -0.029281616f,
   -0.025817871f, -0.150497437f, -0.297210693f, -1.077117920f,
  	0.841949463f,  0.032379150f,  0.129562378f, -0.017257690f,
  	0.017349243f, -0.003280640f,  0.001037598f,  0.000030518f,
   -0.000167847f, -0.002349854f, -0.000549316f, -0.029937744f,
   -0.030609131f, -0.148773193f, -0.323318481f, -1.089782715f,
  	0.816864014f,  0.015228271f,  0.125259399f, -0.019531250f,
    0.016235352f, -0.003372192f,  0.000961304f,  0.000030518f,
   -0.000198364f, -0.002456665f, -0.001098633f, -0.030532837f,
   -0.035552979f, -0.146362305f, -0.349868774f, -1.101211548f,
  	0.791213989f, -0.001068115f,  0.120697021f, -0.021575928f,
  	0.015121460f, -0.003417969f,  0.000885010f,  0.000030518f,
   -0.000213623f, -0.002578735f, -0.001693726f, -0.031005859f,
   -0.040634155f, -0.143264771f, -0.376800537f, -1.111373901f,
    0.765029907f, -0.016510010f,  0.115921021f, -0.023422241f,
    0.014022827f, -0.003463745f,  0.000808716f,  0.000030518f,
   -0.000244141f, -0.002685547f, -0.002334595f, -0.031387329f,
   -0.045837402f, -0.139450073f, -0.404083252f, -1.120223999f,
    0.738372803f, -0.031082153f,  0.110946655f, -0.025085449f,
  	0.012939453f, -0.003479004f,  0.000747681f,  0.000015259f,
   -0.000259399f, -0.002792358f, -0.003005981f, -0.031661987f,
   -0.051132202f, -0.134887695f, -0.431655884f, -1.127746582f,
  	0.711318970f, -0.044784546f,  0.105819702f, -0.026535034f,
    0.011886597f, -0.003479004f,  0.000686646f,  0.000015259f,
   -0.000289917f, -0.002899170f, -0.003723145f, -0.031814575f,
   -0.056533813f, -0.129577637f, -0.459472656f, -1.133926392f,
    0.683914185f, -0.057617188f,  0.100540161f, -0.027801514f,
  	0.010848999f, -0.003463745f,  0.000625610f,  0.000015259f,
   -0.000320435f, -0.002990723f, -0.004486084f, -0.031845093f,
   -0.061996460f, -0.123474121f, -0.487472534f, -1.138763428f,
  	0.656219482f, -0.069595337f,  0.095169067f, -0.028884888f,
  	0.009841919f, -0.003433228f,  0.000579834f,  0.000015259f,
   -0.000366211f, -0.003082275f, -0.005294800f, -0.031738281f,
   -0.067520142f, -0.116577148f, -0.515609741f, -1.142211914f,
  	0.628295898f, -0.080688477f,  0.089706421f, -0.029785156f,
  	0.008865356f, -0.003387451f,  0.000534058f,  0.000015259f,
   -0.000396729f, -0.003173828f, -0.006118774f, -0.031478882f,
   -0.073059082f, -0.108856201f, -0.543823242f, -1.144287109f,
  	0.600219727f, -0.090927124f,  0.084182739f, -0.030517578f,
	0.007919312f, -0.003326416f,  0.000473022f,  0.000015259f
	};
  */
  
}
