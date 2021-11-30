%System of differential equations, each equation representing the rate of
%change of each population between each time point. There are two
%ecosystems (x & y), each with 4 populations considered (N, P, H, D)
function xdot=MyModelFx(~,x,p)
xdot=zeros(8,1);
%dN_x/dt
xdot(1,1)=-p.alphax*x(1)*x(3)+p.r*(1-p.eD)*x(7)+p.dN*(x(1)-x(2));
%dN_y/dt
xdot(2,1)=-p.alphay*x(2)*x(4)+p.r*(1-p.eD)*x(8)+p.dN*(x(2)-x(1));
%dP_x/dt
xdot(3,1)=p.alphax*x(1)*x(3)*(1-(x(3)/x(7)))-p.mP*x(3)-p.betax*x(3)*x(5)+p.dP*(x(3)-x(4));
%dP_y/dt
xdot(4,1)=p.alphay*x(2)*x(4)*(1-(x(4)/x(8)))-p.mP*x(4)-p.betay*x(4)*x(6)+p.dP*(x(4)-x(3));
%dH_x/dt
xdot(5,1)=p.betax*x(3)*x(5)-p.mH*x(5)+p.dH*(x(5)-x(6));
%dH_y/dt
xdot(6,1)=p.betay*x(4)*x(6)-p.mH*x(6)+p.dH*(x(6)-x(5));
%dD_x/dt
xdot(7,1)=p.Ix-p.eD*x(7)+p.mP*x(3)+p.mH*x(5)-p.r*x(7)+p.dD*(x(7)-x(8));
%dD_y/dt
xdot(8,1)=p.Iy-p.eD*x(8)+p.mP*x(4)+p.mH*x(6)-p.r*x(8)+p.dD*(x(8)-x(7));
