#define scr_particle


particle1 = part_type_create();
part_type_shape(particle1,pt_shape_pixel);
part_type_size(particle1,0.87,0.97,-0.03,1);
part_type_scale(particle1,1.02,1.79);
part_type_color3(particle1,2647551,15528400,2076461);
part_type_alpha3(particle1,0.79,0.55,0.06);
part_type_speed(particle1,1.12,4.15,0.18,4);
part_type_direction(particle1,156,344,-1,1);
part_type_gravity(particle1,0,270);
part_type_orientation(particle1,2,192,0.40,8,0);
part_type_blend(particle1,0);
part_type_life(particle1,53,59);

emitter1 = part_emitter_create(Sname);
part_emitter_region(Sname,emitter1,x,x,y,y,0,0);
part_emitter_stream(Sname,emitter1,particle1,5);


