
#define PROCESSING_TEXTURE_SHADER

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

varying vec4 vertColor;
varying vec4 vertTexCoord;

uniform float time;
uniform vec2 resolution;

vec4 live = vec4(0.5,1.0,0.7,1.);
vec4 dead = vec4(0.,0.,0.,1.);
vec4 blue = vec4(0.,0.,1.,1.);

void main() {

		float rnd1 = mod(fract(sin(dot(vertTexCoord.xy + time * 0.001, vec2(14.9898,78.233))) * 43758.5453), 1.0);
		if (rnd1 > 0.5) {
			gl_FragColor = live;
		} else {
			gl_FragColor = blue;
		}
		
  //gl_FragColor = vertColor;
  //gl_FragColor = vec4(vec3(1) - vertColor.xyz, 1);
}