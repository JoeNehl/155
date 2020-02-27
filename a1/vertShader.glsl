#version 430

uniform float x;
uniform float y;
uniform float inctheta;
uniform float incx;
uniform float incy;
uniform float scale;
uniform int grad;
uniform int motion;

out vec4 inColor;

void main(void)
{ 	
	if (gl_VertexID == 0){
		if (motion==2){
			gl_Position = vec4(scale * ((0.25) + (0.5 * cos( radians(inctheta)))), scale * ((-0.25) + (0.5 * sin( radians(inctheta)))), 0.0, 1.0);
		}else{
			gl_Position = vec4(scale * (0.25+incx), scale * (-0.25+incy), 0.0, 1.0);
		}
		if (grad==1){
			inColor = vec4(1.0, 0.00, 0.00, 1.0);
		}else{
			inColor = vec4(1.0, 0.00, 0.00, 1.0);
		}
	}else if (gl_VertexID == 1) {
		if (motion==2){
			gl_Position = vec4(scale * ((-0.25) + (0.5 * cos( radians(inctheta)))), scale * ((-0.25) + (0.5 * sin( radians(inctheta)))), 0.0, 1.0);
		}else {
			gl_Position = vec4(scale * (-0.25+incx), scale* (-0.25+incy), 0.0, 1.0);
		}
		if (grad==1){
			inColor = vec4(0.00, 1.00, 0.00, 1.0);
		}else{
			inColor = vec4(1.0, 0.00, 0.00, 1.0);
		}
	}else {
		if (motion==2){
			gl_Position = vec4(scale * ((0.00) + (0.5 * cos( radians(inctheta)))), scale* ((0.00) + (0.5 * sin( radians(inctheta)))), 0.0, 1.0);
		}else {
			gl_Position = vec4(scale * (0.00+incx),scale * (0.00+incy), 0.0, 1.0);
		}
		if (grad==1){
			inColor = vec4(0.00, 0.00, 1.00, 1.0);
		}else{
			inColor = vec4(1.0, 0.00, 0.00, 1.0);
		}
	}
}