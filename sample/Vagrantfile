# -*- mode: ruby -*-

def f(path)
  File.dirname(__FILE__) + "/" + path
end

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.network :forwarded_port, guest: 8080, host: 8081
  config.vm.network :forwarded_port, guest: 80, host: 8082
  config.vm.network :forwarded_port, guest: 4242, host: 4242 # nrepl
  config.vm.synced_folder ".", "/var/projects/vagrant-sample"
  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
    v.cpus = 2
  end
  config.vm.provision :ansible do |ansible|
    ansible.playbook = f "ansible/provision.yml"
    ansible.inventory_path = f "ansible/dev"
    ansible.limit = 'all'
  end
  config.ssh.username = 'vagrant'
end
